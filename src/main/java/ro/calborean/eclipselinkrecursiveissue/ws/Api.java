package ro.calborean.eclipselinkrecursiveissue.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import ro.calborean.eclipselinkrecursiveissue.ws.repo.MainEntityBean;
import ro.calborean.eclipselinkrecursiveissue.ws.repo.ParentEntityBean;
import ro.calborean.eclipselinkrecursiveissue.ws.repo.entities.MainEntity;
import ro.calborean.eclipselinkrecursiveissue.ws.repo.entities.ParentEntity;


@WebService(serviceName = "ApiService")
public class Api {

    @EJB
    ParentEntityBean bean;
    @EJB
    MainEntityBean mainBean;
    
    @WebMethod(operationName = "getData")
    public String getData() {
        ParentEntity p = new ParentEntity();
        p.setIdParent(1l);
        List<ParentEntity> parents = bean.find(p);
        
        MainEntity m = new MainEntity();
        m.setParent(parents.get(0));
        List<MainEntity> rez = mainBean.find(m);
        return rez.get(0).getName();
    }
    
}
