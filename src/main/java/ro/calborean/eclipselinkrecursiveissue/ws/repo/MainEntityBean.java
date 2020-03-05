package ro.calborean.eclipselinkrecursiveissue.ws.repo;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import ro.calborean.eclipselinkrecursiveissue.ws.repo.entities.MainEntity;

@Startup
@Stateless
public class MainEntityBean extends CrudRepoBean<MainEntity>{
    
}
