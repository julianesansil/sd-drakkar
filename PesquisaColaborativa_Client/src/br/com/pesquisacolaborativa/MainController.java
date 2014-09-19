/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pesquisacolaborativa;

import controllers.FrameController;
import drakkar.prow.DrakkarProw;
import java.io.Serializable;
import ui.MainFrame;

/**
 *
 * @author Juliane
 */
public class MainController implements Serializable {
    private static FLogin fLogin;
    private static FSearch fSearch;
    
    private static String host;
    private static String search;
    private static DrakkarProw client;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        showLogin();
        while (validateLogin()) {
            host = getHost();
        }
        showSearch();

        try {
            String[] param = {"-sh", host};
            client = new DrakkarProw(param);
            MainFrame mainFrame = new MainFrame(client);
            
            FrameController frameController = new FrameController(mainFrame);
            while (search == null)
                search = getSearch();
            frameController.search(search);
        }
         catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static String getHost() {
        return fLogin.getHost();
    }

    public static String getSearch() {
        return fSearch.getSearch();
    }
    
    public static void showLogin() {
        fLogin = new FLogin();
        fLogin.setVisible(true);
    }
    
    public static boolean validateLogin() {
        //Validar login
        if (host == null)
            return true;
        return false;
    }

    public static void showSearch() {
        fSearch = new FSearch();
        fSearch.setVisible(true);
    }

}
