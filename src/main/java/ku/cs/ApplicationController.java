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

import java.io.IOException;

public class ApplicationController extends FXRouter {

    private static double[]  offset_XY,pref_WH;
    private static Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();


    //private static Parent root;

    public static void goTo(String routeLabel) throws IOException {
        RouteScene route = (RouteScene)routes.get(routeLabel);
        loadNewRoute(route);


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


}
