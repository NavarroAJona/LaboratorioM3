package cr.ac.ucr.ecci.mapasucr;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback {
    private GoogleMap mMap;
    private Boolean controlsEnabled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
    @Override
    // Creamos el menu de opciones
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu: this adds items to the action bar if it is present
                getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    // Implementamos las llamadas al menú de opciones
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_1) {
            // Marcador soda UCR
            marcadorSodaUCR();
            return true;
        } else if (id == R.id.action_2) {
            // Mostrar las opciones de Zoom
            mostrarZoom();
            return true;
        } else if (id == R.id.action_3) {
            // Mover la posicion de la camara y hacer Zoom
            posicionCamaraZoom();
            return true;
        } else if (id == R.id.action_4) {
            // Cambiar el tipo de mapa
            mapaSatelite();
            return true;
        } else if (id == R.id.action_5) {
            // Cambiar el tipo de mapa
            mapaHibrido();
            return true;
        } else if (id == R.id.action_6) {
            // Cambiar el tipo de mapa
            mapaNinguno();
            return true;
        } else if (id == R.id.action_7) {
            // Cambiar el tipo de mapa
            mapaNormal();
            return true;
        } else if (id == R.id.action_8) {
            // Cambiar el tipo de mapa
            mapaTierra();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void marcadorSodaUCR() {
        // Agregar un marcador en la antigua soda de odontología
        LatLng sodaOdontologia = new LatLng(9.938035, -84.051509);
        // Agregar marcador
        mMap.addMarker(new
                MarkerOptions().position(sodaOdontologia).title("Antigua Soda de Odontología de la UCR"));
                // Mover la "cámara" del mapa hacia el marcador
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sodaOdontologia));
    }
    public void mostrarZoom() {
        controlsEnabled = !controlsEnabled;
        UiSettings mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(controlsEnabled);
        mapSettings.setCompassEnabled(controlsEnabled);
    }
    // Mover la cámara y hacer zoom
    public void posicionCamaraZoom() {
        LatLng sodaOdontologia = new LatLng(9.938035, -84.051509);
// Mover la cámara
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(sodaOdontologia)
                .zoom(20) // zoom level
                .bearing(70) // bearing // direccion de la camara
                .tilt(25) // tilt angle // inclinacion
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    public void mapaSatelite() {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); }
    public void mapaHibrido() { mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); }
    public void mapaNinguno() { mMap.setMapType(GoogleMap.MAP_TYPE_NONE); }
    public void mapaNormal() { mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); }
    public void mapaTierra() { mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); }
}
