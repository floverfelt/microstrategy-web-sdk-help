public class CustomTask extends AbstractAppTask {

    private TaskParameterMetadata exportReturnParam;

    public CustomTask() {
        super("A task which parses a document and returns its data.");
        this.addMessageIDParam(true, null);
        this.addSessionStateParam(true, null);
        exportReturnParam = addParameterMetadata("exportReturnParam", "Param to return or export", true, null);
    }

    @Override
    public void processRequest(TaskRequestContext context, MarkupOutput markupOutput) {

        try {
            // Spawn the iServerSession from the param
            WebIServerSession webIServerSession = context.getWebIServerSession(PARAM_NAME_SESSION_STATE, null);

            // Collection of objects in the request to the task
            RequestKeys requestKeys = context.getRequestKeys();

            // Spawned instance of the document in code
            RWInstance rwInstance = webIServerSession.getFactory().getRWSource().getInstance(msgIDParam.getValue(requestKeys));

            RWDefinition rwDefinition = rwInstance.getDefinition();
            // Only grid on the doc
            RWUnitDef rwUnitDef = rwDefinition.findUnitsByType(EnumRWUnitTypes.RWUNIT_GRIDGRAPH)[0];
            RWData rwData = rwInstance.getData();
            RWGridGraphObject rwGridGraphObject = (RWGridGraphObject) rwData.findUnits(rwUnitDef.getKey()).get(0);

            WebViewInstance webViewInstance = (WebViewInstance) rwGridGraphObject.getValue();
            WebReportGrid webReportGrid = webViewInstance.getGridData().getWebReportGrid();

            WebGridRows webGridRows = webReportGrid.getGridRows();

            ArrayList<String> yearMonths = new ArrayList<>();

            for(int i=0; i < webGridRows.size(); i++){
                WebRow webRow = webGridRows.get(i);
                System.out.println(webRow.getHeaderElements().get(0).getDisplayName());
                yearMonths.add(webRow.getHeaderElements().get(0).getDisplayName());
            }

            if(exportReturnParam.getValue(requestKeys).equalsIgnoreCase("export")) {
                // send off the list somewhere else
            }

            // Add it to the output
            markupOutput.append(yearMonths.toString());
        }
        catch (Exception ex) {
            // Handle exception however you want
            ex.printStackTrace();
        }

    }

}
