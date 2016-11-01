package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import dam.isi.frsf.utn.edu.ar.laboratorio04.R;

/**
 * Created by denis on 1/11/2016.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

    }
}
