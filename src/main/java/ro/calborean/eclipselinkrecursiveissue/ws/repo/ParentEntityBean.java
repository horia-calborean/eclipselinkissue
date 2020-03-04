package ro.calborean.eclipselinkrecursiveissue.ws.repo;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import ro.calborean.eclipselinkrecursiveissue.ws.repo.entities.ParentEntity;

@Startup
@Stateless
public class ParentEntityBean extends CrudRepoBean<ParentEntity>{
    
}
