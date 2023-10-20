package com.example.semana9gps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;

public class MainActivity extends AppCompatActivity {

    private EditText etLongitud;
    private EditText etLatitud;
    private Button btnObtenerUbicacion;
    private TextView tvLongitud;
    private TextView tvLatitud;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLongitud = findViewById(R.id.etLongitud);
        etLatitud = findViewById(R.id.etLatitud);
        btnObtenerUbicacion = findViewById(R.id.btnObtenerUbicacion);
        tvLongitud = findViewById(R.id.tvLongitud);
        tvLatitud = findViewById(R.id.tvLatitud);

        // Comprobar y solicitar permisos de ubicación
        checkLocationPermission();

        // Configurar el administrador de ubicación
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Obtener la latitud y longitud actual
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                // Mostrar las coordenadas en los TextView
                tvLatitud.setText("Latitud: " + latitude);
                tvLongitud.setText("Longitud: " + longitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        // Configurar el botón para obtener la ubicación
        btnObtenerUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerUbicacionManual();
            }
        });
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Si los permisos no están otorgados, solicítalos.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Los permisos ya están otorgados, puedes iniciar la obtención de ubicación aquí.
            // Puedes usar las clases LocationManager o FusedLocationProviderClient para obtener la ubicación.
        }
    }

    private void obtenerUbicacionManual() {
        try {
            double latitude = Double.parseDouble(etLatitud.getText().toString());
            double longitude = Double.parseDouble(etLongitud.getText().toString());

            // Mostrar las coordenadas ingresadas en los TextView
            tvLatitud.setText("Latitud: " + latitude);
            tvLongitud.setText("Longitud: " + longitude);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingresa valores válidos para latitud y longitud.", Toast.LENGTH_SHORT).show();
        }
    }

    // Agrega métodos para manejar la respuesta de la solicitud de permisos y para obtener la ubicación.

    // ...
}
