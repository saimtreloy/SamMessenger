package saim.com.sammessenger.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import saim.com.sammessenger.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRequest extends Fragment {


    public FragmentRequest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fragment_request, container, false);
    }

}
