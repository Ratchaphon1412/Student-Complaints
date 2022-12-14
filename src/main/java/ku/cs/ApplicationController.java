package ku.cs;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ku.cs.controller.admin.AgencyLoad;
import ku.cs.controller.Reposthable;

import java.io.IOException;

public class ApplicationController extends FXRouter {
    private String preloads;

    ApplicationController(String preloads){
        this.preloads = preloads;
    }

    private static double[]  offset_XY,pref_WH;
    private static Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();


    //private static Parent root;

    public static void goTo(String routeLabel) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewRoute(route);
    }

    public static void goToCenter(String routeLabel) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewRouteCenter(route);
    }

    public static void goTo(String routeLabel, Object data) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.data = data;
        loadNewRoute(route);
    }

    public static void loadNewRoute(RouteScene route) throws IOException {
        currentRoute = route;
        String scenePath = "/" + route.scenePath;
        Parent resource = (Parent) FXMLLoader.load((new Object() {
        }).getClass().getResource(scenePath));
        window.setTitle(route.windowTitle);
        Scene scene = new Scene(resource,route.sceneWidth, route.sceneHeight);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.show();
        routeAnimation(resource);
        dragWindow(resource,window);
    }

    public static void loadNewRouteCenter(RouteScene route) throws IOException {
        currentRoute = route;
        String scenePath = "/" + route.scenePath;
        Parent resource = (Parent) FXMLLoader.load((new Object() {
        }).getClass().getResource(scenePath));
        window.setTitle(route.windowTitle);
        Scene scene = new Scene(resource,route.sceneWidth, route.sceneHeight);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth())/2);
        window.setY((primScreenBounds.getHeight() - window.getHeight())/2);

        routeAnimation(resource);
        dragWindow(resource,window);
    }

    public static void dragWindow(Parent root, Stage stage){

        root.setOnMousePressed((MouseEvent p) -> {
            offset_XY = new double[]{p.getSceneX(), p.getSceneY()};
        });

        root.setOnMouseDragged((MouseEvent d) -> {
            //Ensures the stage is not dragged past the taskbar
            if (d.getScreenY()<(SCREEN_BOUNDS.getMaxY()-20))
                stage.setY(d.getScreenY() - offset_XY[1]);
            stage.setX(d.getScreenX() - offset_XY[0]);
        });

        root.setOnMouseReleased((MouseEvent r)-> {
            //Ensures the stage is not dragged past top of screen
            if (stage.getY()<0.0) stage.setY(0.0);
        });
    }


    public static void goToNew(String routeLabel) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        createDialog(route);

    }
    public static void goToNew(String routeLabel,String text) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.text = text ;
        createDialog(route);

    }

    public static void goToNew(String routeLabel,Object object) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.data = object;
        createDialog(route);
    }

    public static  void goToNew(String routeLabel , AgencyLoad agencyLoad) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.agencyLoad = agencyLoad ;
        createDialog(route);
    }
    public static void goToNew(String routeLabel, Object object, Reposthable reposthable) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.data = object;
        route.reposthable = reposthable;
        createDialog(route);
    }

    public static void goToNew(String routeLabel,Object object,String text) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        route.data = object;
        route.requestText = text;
        createDialog(route);
    }


    public static void createDialog(RouteScene route) throws IOException {


        currentRoute = route;
        String scenePath = "/" + route.scenePath;
        Parent resource = (Parent) FXMLLoader.load((new Object() {
        }).getClass().getResource(scenePath));

        Stage stage = new Stage();
        Scene scene = new Scene(resource,route.sceneWidth, route.sceneHeight);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);//hidding titlebar
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        dragWindow(resource,stage);
    }

    public static void preloadToLogin(String routeLabel) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewRoute(route);

    }


}
