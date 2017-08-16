package ve.needforock.stressless.data;

import java.util.ArrayList;
import java.util.List;

import ve.needforock.stressless.models.Pending;

/**
 * Created by Soporte on 14-Aug-17.
 */

public class Queries {

    public List<Pending> pendings(){
        List<Pending> pendings = new ArrayList<>();
        List<Pending> pendingList = Pending.find(Pending.class, "done = 0");

        if(pendingList != null && pendingList.size()>0){
            pendings.addAll(pendingList);
        }
        return pendings;
    }
}
