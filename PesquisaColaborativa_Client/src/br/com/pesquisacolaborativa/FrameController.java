package br.com.pesquisacolaborativa;

import drakkar.oar.Request;
import drakkar.oar.Response;
import drakkar.oar.slice.error.RequestException;
import drakkar.oar.util.Invocation;
import drakkar.prow.RequestSearchFactory;
import drakkar.prow.DrakkarProw;
import drakkar.prow.facade.desktop.event.ProwAdapter;
import drakkar.prow.facade.desktop.event.SearchAdapter;
import drakkar.prow.facade.desktop.event.SearchEvent;

public class FrameController {
    private MainFrame mainFrame;
    private DrakkarProw client;
    private ProwAdapter clientAdapter;
    private SearchAdapter searchAdapter;
    
    public FrameController(MainFrame mainFrame) throws RequestException {
        this.mainFrame = mainFrame;
        this.client = mainFrame.getClient();
        
        this.clientAdapter = new ProwAdapter() {};
        this.searchAdapter = new SearchAdapter() {
            @Override
            public void notifySearchResults(SearchEvent evt) {
                super.notifySearchResults(evt);
//                Continua aqui, mas antes tem que implementar o SearchTableModel, EU ACHO!
            }
        };

        client.getClientListenerManager().addClientListener(clientAdapter);
        client.getClientListenerManager().addSearchListener(searchAdapter);
        client.loginSearchCollabSession();
    }
    
    public void search(String search) {
        Request request = RequestSearchFactory.create(search, RequestSearchFactory.META_SEARCH_AND_SPLIT);
        client.send(request, Invocation.SYNCHRONOUS_METHOD_INVOCATION);
    }

}
