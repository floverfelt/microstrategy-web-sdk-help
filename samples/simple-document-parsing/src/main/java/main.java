import com.microstrategy.web.objects.*;
import com.microstrategy.web.objects.rw.*;
import com.microstrategy.webapi.EnumDSSXMLApplicationType;
import com.microstrategy.webapi.EnumDSSXMLStatus;

public class main {

    public static void main(String[] args) throws Exception {

        WebObjectsFactory factory = WebObjectsFactory.getInstance();
        WebIServerSession adminSession = factory.getIServerSession();
        String serverName = "serverName";
        adminSession.setServerName(serverName);
        adminSession.setServerPort(0);
        adminSession.setProjectName("projectName");
        adminSession.setLogin("loginName");
        adminSession.setPassword("plaintextPassword");
        adminSession.setApplicationType(EnumDSSXMLApplicationType.DssXmlApplicationCustomApp);
        adminSession.reconnect();

        RWInstance rwInstance = factory.getRWSource().getNewInstance("your-guid-here");

        rwInstance.setAsync(false);
        rwInstance.setPollingFrequency(1000);
        rwInstance.setMaxWait(1000);
        int status = rwInstance.pollStatus();
        if (status != EnumDSSXMLStatus.DssXmlStatusResult)
        {
            throw new RuntimeException("Status code should be one but is: " + status);
        }

        RWDefinition rwDefinition = rwInstance.getDefinition();
        RWUnitDef rwUnitDef = rwDefinition.findUnits(EnumRWUnitTypes.RWUNIT_GRIDGRAPH, "Grid")[0];
        RWData rwData = rwInstance.getData();
        RWGridGraphObject rwGridGraphObject = (RWGridGraphObject) rwData.findUnits(rwUnitDef.getKey()).get(0);

        WebViewInstance webViewInstance = (WebViewInstance) rwGridGraphObject.getValue();
        WebReportGrid webReportGrid = webViewInstance.getGridData().getWebReportGrid();

        WebGridRows webGridRows = webReportGrid.getGridRows();

        for(int i=0; i < webGridRows.size(); i++){
            WebRow webRow = webGridRows.get(i);
            System.out.println(webRow.getHeaderElements().get(0).getDisplayName());
        }
    }
}
