package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import infinity.runapp.R;
import infinity.runapp.getsets.ActiveUser;
import infinity.runapp.getsets.Group;
import infinity.runapp.library.InfinityDBHandler;

/**
 * Created by ADC on 2/9/2015.
 */
public class GroupsFragment extends Fragment
{
    ListView mListView;

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.groups_layout,container,false);

        mListView = (ListView)v.findViewById(R.id.groupList);

        showGroups();

        return v;
    }

    public void showGroups()
    {
        InfinityDBHandler db = new InfinityDBHandler(getActivity(), null, null, 1);

        Integer userID = getUserID();
        Group [] groupList = db.groupList(userID);

        ArrayAdapter<Group> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, groupList);

        mListView.setAdapter(adapter);
    }

    public Integer getUserID(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        ActiveUser activeUser = dbHandler.setUser();

        String userID = String.valueOf(activeUser.getUserID());
        Integer ID = Integer.parseInt(userID);

        return ID;
    }

}
