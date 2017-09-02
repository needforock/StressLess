package ve.needforock.stressless.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ve.needforock.stressless.R;
import ve.needforock.stressless.adapters.PendingAdapter;
import ve.needforock.stressless.adapters.PendingClickListener;
import ve.needforock.stressless.models.Pending;
import ve.needforock.stressless.views.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PendingsFragment extends Fragment implements PendingClickListener {
    public static final String PENDING_ID = "ve.needforock.stressless.views.main.KEY.PENDING_ID";
    PendingAdapter pendingAdapter;


    public PendingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pendingRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        pendingAdapter = new PendingAdapter(this);
        recyclerView.setAdapter(pendingAdapter);


    }



    public void updateList(Pending pending) {
        pendingAdapter.update(pending);
    }

    public void updateByName(String name){
        pendingAdapter.updateByName(name);
    }

    @Override
    public void clickedId(long id) {

        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(PENDING_ID, id);
        startActivity(intent);
    }
}
