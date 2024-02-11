package com.example.beltekproje2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.beltekproje2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int sonuc1 = 0;
    int sonuc2=0;
    int sonuc3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Switch'in true olarak mı false olarak mı başlatılacağını ayarlar
        binding.ortalamaSwc.setChecked(false);
        clearDisable();


        binding.ortalamaSwc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean switchState) {

                    if(switchState){
                        enable();
                        boolean b=binding.edtText.isEnabled();
                        //switchState true ise çalışacak blok bu bloktur.
                        //yani ortalamanın 2.50 üstü olduğunda kullanıcın
                        //işlemine devam etmesini sağlayan blok.
                        binding.sonucTV.setText("Sonuç:");
                        binding.egitimDurumuRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                //Radio Group içerisinde seçili olan radio buuton id'sini döndüren satır.
                                RadioButton selectedButton=findViewById(radioGroup.getCheckedRadioButtonId());
                                if(selectedButton.getText().equals("Ön Lisans")){
                                    sonuc1=10;
                                } else if (selectedButton.getText().equals("Lisans")) {
                                    sonuc1=15;
                                }else {
                                    sonuc1=20;
                                }
                            }
                        });
                        binding.onurBelgesiCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    sonuc2+=20;
                                }else {
                                    sonuc2-=20;
                                }
                            }
                        });
                        binding.ogrgrpCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    sonuc3+=15;
                                }
                                else{
                                    sonuc3-=15;
                                }
                            }
                        });
                        binding.hesaplaBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               String name=binding.edtText.getText().toString();
                               //Ad Soyad Bölümünü kontorl eden kod bloğu
                               if(TextUtils.isEmpty(name)){
                                    // boş işe çalışacak bölüm
                                   Toast.makeText(MainActivity.this, "Lütfen Ad-Soyad Bölümünü Boş Bırakmayınız", Toast.LENGTH_SHORT).show();

                               }else{
                                    // dolu ise çalışacak bölüm
                                   binding.sonucTV.setText("Sonuç : "+(sonuc1+sonuc2+sonuc3+20));
                               }
                            }
                        });


                    }else{
                        clearDisable();
                        binding.sonucTV.setText("Sonuç: Hesaplanamadı Tekrar Deneyiniz");
                    }
            }


        });


    }
/////////////////////////////////////////////////////////////


    private void enable() {
        binding.egitimDurumuRG.getChildAt(0).setEnabled(true);
        binding.egitimDurumuRG.getChildAt(1).setEnabled(true);
        binding.egitimDurumuRG.getChildAt(2).setEnabled(true);
        binding.egitimDurumuRG.setEnabled(true);
        binding.onurBelgesiCB.setEnabled(true);
        binding.ogrgrpCB.setEnabled(true);
        binding.hesaplaBTN.setEnabled(true);
    }

    private void clearDisable() {
        //CheckBox yapısının işaretli olup olamayacağını belirleyen metot
        binding.ogrgrpCB.setChecked(false);
        binding.onurBelgesiCB.setChecked(false);
        //Radio Group içerisindeki seçimi temizleyen metot
        binding.egitimDurumuRG.clearCheck();
        //setEnabled metodu ise bu yapılarının aktif olup olmadığını ayarlayan metotdur.
        binding.egitimDurumuRG.getChildAt(0).setEnabled(false);
        binding.egitimDurumuRG.getChildAt(1).setEnabled(false);
        binding.egitimDurumuRG.getChildAt(2).setEnabled(false);
        binding.onurBelgesiCB.setEnabled(false);
        binding.ogrgrpCB.setEnabled(false);
        binding.hesaplaBTN.setEnabled(false);
    }












}
