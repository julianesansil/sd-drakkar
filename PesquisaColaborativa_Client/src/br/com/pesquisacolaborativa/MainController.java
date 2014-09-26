package br.com.pesquisacolaborativa;

import drakkar.prow.DrakkarProw;

public class MainController {
    private static FLogin fLogin;
    
    private static DrakkarProw client;
    private static String host;

    public static String getHost() {
        return fLogin.getHost();
    }

    public static void showLogin() {
        fLogin = new FLogin();
        fLogin.setVisible(true);
    }
    
    public static boolean validateLogin() {
        //Validar login
        return host == null;
    }

    public static void main(String[] args) {
        showLogin();
        
        while (validateLogin()) {
            host = getHost();
        }

        try {
            String[] param = {"-sh", host};
            client = new DrakkarProw(param);

            MainFrame mainFrame = new MainFrame(client);
            mainFrame.go();
        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }

}
