package com.savenow;

import java.util.HashMap;
import java.util.Map;

import com.savenow.model.repositories.BoxRepository;
import com.savenow.shared.common.enums.ProgramExecution;
import com.savenow.shared.common.interfaces.IView;
import com.savenow.shared.interfaces.box.IBoxController;
import com.savenow.shared.interfaces.box.IBoxRepository;
import com.savenow.view.BoxView;
import com.savenow.view.HomeView;
import com.savenow.view.Routes;
import com.savenow.controller.BoxController;
import com.savenow.view.uiUtils.MenuUtils;
import com.savenow.view.uiUtils.PromptUtils;
import com.savenow.view.uiUtils.UiConstants;


public class Main {
    public static void main(String[] args) {
        Map<String, IView> viewsConfig = configureViews();
        Map<Integer, Routes> routesConfig = configureRoutes();
        MenuUtils.printBannerAndMenu();

        while (true) {
            int selectedOption = PromptUtils.inputInteger(UiConstants.enterOptionCopy, "selection");
            Routes selectedRoute = routesConfig.get(selectedOption);
            ProgramExecution programFlowExecution = executeView(selectedRoute, viewsConfig);

            if (programFlowExecution == ProgramExecution.EXIT) {
                return;
            }

            if(selectedRoute != Routes.HOME_ROUTE) {
                System.out.println();
                System.out.println(UiConstants.menu);
            }
        }
    }

    private static Map<String, IView> configureViews() {
        Map<String, IView> viewMapper = new HashMap<>();

        // Home IoC confication
        IView homeView = new HomeView();

        // Box IoC configuration
        IBoxRepository boxRepository = new BoxRepository();
        IBoxController boxController = new BoxController(boxRepository);
        IView boxView = new BoxView(boxController);

        viewMapper.put(homeView.toString(), homeView);
        viewMapper.put(boxView.toString(), boxView);

        return viewMapper;
    }

    /**
     * In charge of configuring the system routes
     * @return a map of integer value as key with route enum string as value
     */
    private static Map<Integer, Routes> configureRoutes() {
        Map<Integer, Routes> routesMapper = new HashMap<>();
        routesMapper.put(0, Routes.HOME_ROUTE);
        routesMapper.put(1, Routes.ALL_BOXES_LIST_ROUTE);
        routesMapper.put(2, Routes.CREATE_BOX_ROUTE);
        routesMapper.put(3, Routes.ALL_TRANSACTIONS_ROUTE);
        routesMapper.put(4, Routes.CREATE_TRANSACTION_ROUTE);
        routesMapper.put(5, Routes.EXIT_ROUTE);
        return routesMapper;
    }

    /**
     * In charge of executing views.
     * @param route: represents the system route
     * @return ProgramExecution enumeration
     */
    private static ProgramExecution executeView(Routes route, Map<String, IView> views) {
        final HomeView homeView = (HomeView) views.get("HomeView");
        final BoxView boxView = (BoxView) views.get("BoxView");

        return switch (route) {
            case HOME_ROUTE -> {
                homeView.homeView();
                yield ProgramExecution.CONTINUE;
            }
            case ALL_BOXES_LIST_ROUTE -> {
                boxView.listBoxesView();
                yield ProgramExecution.CONTINUE;
            }
            case CREATE_BOX_ROUTE -> {
                boxView.addBoxView();
                yield ProgramExecution.CONTINUE;
            }
            case ALL_TRANSACTIONS_ROUTE -> {
                System.out.println("All transaction route");
                yield ProgramExecution.CONTINUE;
            }
            case CREATE_TRANSACTION_ROUTE -> {
                System.out.println("Create transaction route");
                yield ProgramExecution.CONTINUE;
            }
            case EXIT_ROUTE -> {
                System.out.println("Exit route");
                yield ProgramExecution.EXIT;
            }
				};
    }
}