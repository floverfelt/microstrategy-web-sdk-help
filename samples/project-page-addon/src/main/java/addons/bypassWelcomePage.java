package addons;

import com.microstrategy.web.app.ProjectInformation;
import com.microstrategy.web.app.WebAppSessionManager;
import com.microstrategy.web.app.addons.AbstractAppAddOn;
import com.microstrategy.web.app.beans.PageComponent;
import com.microstrategy.web.platform.ContainerServices;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class bypassWelcomePage extends AbstractAppAddOn {

    @Override
    public String getAddOnDescription() {
        return null;
    }

    @Override
    public void postCollectData(PageComponent pageComponent) {

        try{
            ContainerServices containerServices = pageComponent.getAppContext().getContainerServices();
            WebAppSessionManager webAppSessionManager = pageComponent.getAppContext().getAppSessionManager();
            ArrayList projects = webAppSessionManager.getProjectsList(true);
            ProjectInformation projectInformation = (ProjectInformation) projects.get(0);
            String redirectUrl = "?evt=3010&server=" + projectInformation.getServerName()
                    + "&project=" + projectInformation.getProjectName() + "&port=" + projectInformation.getPortNumber();
            containerServices.setStatusCode(HttpURLConnection.HTTP_SEE_OTHER);
            containerServices.setHeaderValue("Location", redirectUrl);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
