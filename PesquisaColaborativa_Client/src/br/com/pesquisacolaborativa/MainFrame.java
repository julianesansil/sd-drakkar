package br.com.pesquisacolaborativa;

import drakkar.oar.slice.error.RequestException;
import drakkar.prow.DrakkarProw;

public class MainFrame {
    private FSearch fSearch;
    private FrameController fController;

    private DrakkarProw client;
    private String search;

    
    public MainFrame(DrakkarProw client) {
        this.client = client;
    }
    
    public String getSearch() {
        return fSearch.getSearch();
    }

    public DrakkarProw getClient() {
        return client;
    }
    
    public void showSearch() {
        fSearch = new FSearch();
        fSearch.setVisible(true);
    }
    
    public void go() throws RequestException {
        showSearch();

        fController = new FrameController(this);
        while (search == null)
            search = getSearch();
        fController.search(search);
    }
    
}
