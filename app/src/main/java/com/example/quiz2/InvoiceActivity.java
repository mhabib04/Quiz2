package com.example.quiz2;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz2.databinding.ActivityInvoiceBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class InvoiceActivity extends AppCompatActivity {

    private ActivityInvoiceBinding binding;
    public static final String KEY_DATA = "key_data";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Cost cost;
        if (Build.VERSION.SDK_INT >= 33){
            cost = getIntent().getParcelableExtra(KEY_DATA, Cost.class);
        } else {
            cost = getIntent().getParcelableExtra(KEY_DATA);
        }

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        String type = cost.getType();
        String tambahan = cost.getTambahan();
        int waktu = cost.getWaktu();
        int tarifType = cost.getTarifType();
        int tarifTambahan = cost.getTarifTambahan();
        int total = (tarifType * waktu) + tarifTambahan;



        binding.txtType.setText("Type : " + type + " (" + formatRupiah.format((double)tarifType) + ")");
        binding.txtTambahan.setText(tambahan + " : " + formatRupiah.format((double)tarifTambahan));
        binding.txtWaktu.setText("Waktu : " + waktu + " jam");
        binding.txtTotal.setText("Total : " + formatRupiah.format((double)total));

    }
}