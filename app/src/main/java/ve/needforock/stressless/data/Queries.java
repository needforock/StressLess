package ve.needforock.stressless.data;

import android.util.Log;

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

    public List<String> names(){
        List<String> names = new ArrayList<>();
        List<Pending> pendings = pendings();
        for (int i = 0; i <pendings.size() ; i++) {
            names.add(pendings.get(i).getName());
            Log.d("names",pendings.get(i).getName() );
        }
        return names;
    }

    public List<Pending> byName(String name){
        List<Pending> pendings = new ArrayList<>();
        String query = "done = 0 AND name LIKE '%"+name+"%'";
        List<Pending> pendingList = Pending.find(Pending.class,query);
        if(pendingList!=null && pendingList.size()>0){
            pendings.addAll(pendingList);
        }
        return pendings;
    }
}
