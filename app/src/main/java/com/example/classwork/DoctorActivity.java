package com.example.classwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.classwork.adapters.ConsultAdapter;
import com.example.classwork.models.Consult;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Consult> list;
    private ConsultAdapter adapter;
    //private Button btnAdd;
    public static final String KEY1 = "key";
    private static final int REQUEST_CODE = 2;
    public static final int REQUEST_COD = 3;
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_doctor);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.navigation_doctor:
                        return true;
                    case R.id.navigation_patient:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.rv_doctors);
//        btnAdd = findViewById(R.id.btnAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Consult>();
        adapter = new ConsultAdapter((ArrayList<Consult>) list, this);
        setInitialData();
        recyclerView.setAdapter(adapter);


        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent();
//                startActivityForResult(intent, REQUEST_CODE);
//            }
//        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//
//            Consult consult = (Consult) data.getSerializableExtra(ApplicationActivity.KEY);
//            adapter.addData(consult);
//
//        }
//        if (requestCode == REQUEST_COD && resultCode == RESULT_OK && data != null) {
//            Consult consult = (Consult) data.getSerializableExtra(ApplicationActivity.KEY);
//            adapter.changeData(consult, position);
//        }

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN,

            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT

    ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int positionDrag = viewHolder.getAdapterPosition();
            int positionTarget = target.getAdapterPosition();

            Collections.swap(adapter.list, positionDrag, positionTarget);
            adapter.notifyItemMoved(positionDrag, positionTarget);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.list.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };


    public void onItemClick(int position) {
//        this.position =position;
//        Intent intent = new Intent(MainActivity.this, ApplicationActivity.class);
//        intent.putExtra(KEY1, adapter.list.get(position));
//        startActivityForResult(intent, REQUEST_COD);
//

    }
    public void setInitialData(){
        list.add(new Consult("Равшан", "Кабинет 208", R.drawable.doctor1));
        list.add(new Consult("Сергей", "Кабинет 308", R.drawable.doctor2));
        list.add(new Consult("Максим", "Кабинет 408", R.drawable.doctor3));
        list.add(new Consult("Алексей", "Кабинет 508", R.drawable.doctor5));
        list.add(new Consult("Рабат", "Кабинет 608", R.drawable.doctor4));
        list.add(new Consult("Алим", "Кабинет 708", R.drawable.doctor2));
        list.add(new Consult("Алакен", "Кабинет 808", R.drawable.doctor5));
        list.add(new Consult("Адис", "Кабинет 908", R.drawable.doctor1));
        list.add(new Consult("Исмаил", "Кабинет 108", R.drawable.doctor2));
        list.add(new Consult("Эрсултан", "Кабинет 8", R.drawable.doctor3));
        list.add(new Consult("Омурбек", "Кабинет 208", R.drawable.doctor4));
    }

}
