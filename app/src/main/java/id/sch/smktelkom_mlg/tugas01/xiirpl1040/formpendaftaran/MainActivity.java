package id.sch.smktelkom_mlg.tugas01.xiirpl1040.formpendaftaran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nama, tgl_lahir, bulan_lahir, tahun_lahir, alasan;
    Spinner kelas;
    RadioGroup SB;
    RadioButton SB_sudah, SB_belum;
    CheckBox offense, defense, special, setuju;
    Button proses, daftar;
    TextView hasil_nama, hasil_kelas, hasil_ttl, hasil_umur, hasil_SB, hasil_tim, hasil_alasan;
    LinearLayout llHasil;
    List<String> list_kelas;
    ArrayAdapter<String> spinnerAdapter;
    private int year;
    private String _sb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (EditText) findViewById(R.id.etNama);
        tgl_lahir = (EditText) findViewById(R.id.etTgl);
        bulan_lahir = (EditText) findViewById(R.id.etBulan);
        tahun_lahir = (EditText) findViewById(R.id.etTahun);
        alasan = (EditText) findViewById(R.id.etAlasan);
        kelas = (Spinner) findViewById(R.id.spinnerKelas);
        SB = (RadioGroup) findViewById(R.id.rgSB);
        offense = (CheckBox) findViewById(R.id.cbOffense);
        defense = (CheckBox) findViewById(R.id.cbDefense);
        special = (CheckBox) findViewById(R.id.cbSpecial);
        setuju = (CheckBox) findViewById(R.id.cbAgree);
        proses = (Button) findViewById(R.id.btnProcess);
        daftar = (Button) findViewById(R.id.btnRegister);
        hasil_nama = (TextView) findViewById(R.id.tvHasilNama);
        hasil_kelas = (TextView) findViewById(R.id.tvHasilKelas);
        hasil_ttl = (TextView) findViewById(R.id.tvHasilLahir);
        hasil_umur = (TextView) findViewById(R.id.tvHasilUmur);
        hasil_SB = (TextView) findViewById(R.id.tvHasilSB);
        hasil_tim = (TextView) findViewById(R.id.tvHasilTim);
        hasil_alasan = (TextView) findViewById(R.id.tvHasilAlasan);
        llHasil = (LinearLayout) findViewById(R.id.llHasil);
        setSpinnerEntries();

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().toString().equals("")
                        || tgl_lahir.getText().toString().equals("")
                        || bulan_lahir.getText().toString().equals("")
                        || tahun_lahir.getText().toString().equals("")
                        || kelas.getSelectedItem().toString().equals("")
                        || SB.getCheckedRadioButtonId() == -1
                        || alasan.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Masih ada field yang belum diisi", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(tgl_lahir.getText().toString()) < 1 || Integer.parseInt(tgl_lahir.getText().toString()) > 31) {
                    tgl_lahir.setError("Format tanggal salah");
                } else if (Integer.parseInt(bulan_lahir.getText().toString()) < 1 || Integer.parseInt(bulan_lahir.getText().toString()) > 12) {
                    bulan_lahir.setError("Format bulan salah");
                } else if (!offense.isChecked()
                        && !defense.isChecked()
                        && !special.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Anda belum memilih tim", Toast.LENGTH_SHORT).show();
                } else {
                    llHasil.setVisibility(View.VISIBLE);
                    llHasil.requestFocus();
                    hasil_nama.setText("Nama     : " + nama.getText().toString());
                    hasil_kelas.setText("Kelas    : " + kelas.getSelectedItem().toString());
                    hasil_ttl.setText("Lahir    : " + tgl_lahir.getText().toString() + " - " + getMonth(bulan_lahir.getText().toString()) + " - " + tahun_lahir.getText().toString());
                    hasil_umur.setText("Umur     : " + getYear(tahun_lahir.getText().toString()));
                    hasil_tim.setText("Tim      : " + setTim());
                    hasil_alasan.setText("Alasan   : " + alasan.getText().toString());
                    hasil_SB.setText(getSB() + " tahu olahraga Flag Football sebelumnya");
                }
            }
        });
    }

    public String getSB() {
        RadioButton rb = (RadioButton) findViewById(SB.getCheckedRadioButtonId());
        _sb = rb.getText().toString();
        return _sb;
    }

    private String setTim() {
        String tim = "";
        if (offense.isChecked()) {
            tim += "Offense, ";
        }
        if (defense.isChecked()) {
            tim += "Defense, ";
        }
        if (special.isChecked()) {
            tim += "Special.";
        }
        return tim;
    }

    private String getMonth(String bulan) {
        String month = "";
        switch (bulan) {
            case "1":
                month = "Januari";
                break;
            case "2":
                month = "Februari";
                break;
            case "3":
                month = "Maret";
                break;
            case "4":
                month = "April";
                break;
            case "5":
                month = "Mei";
                break;
            case "6":
                month = "Juni";
                break;
            case "7":
                month = "Juli";
                break;
            case "8":
                month = "Agustus";
                break;
            case "9":
                month = "September";
                break;
            case "10":
                month = "Oktober";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "Desember";
                break;
        }
        return month;
    }

    public String getYear(String tahun) {
        year = 2016 - Integer.parseInt(tahun);
        return String.valueOf(year);
    }

    private void setSpinnerEntries() {
        list_kelas = new ArrayList<>();
        for (int i = 10; i <= 11; i++) {
            for (int j = 1; j <= 2; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j == 1) {
                        list_kelas.add(i + " RPL " + k);
                    } else if (j == 2) {
                        list_kelas.add(i + " TKJ " + k);
                    }
                }
            }
        }

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_kelas);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kelas.setAdapter(spinnerAdapter);
    }
}
