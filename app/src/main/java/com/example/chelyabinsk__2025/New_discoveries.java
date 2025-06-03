package com.example.chelyabinsk__2025;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class New_discoveries extends AppCompatActivity {

    private ExpandableListView disc;

    // Группы (области науки)
    private String[] scienceGroups = {
            "Физика",
            "Математика",
            "История",
            "Информатика",
            "Биология",
            "Химия",
            "Экономика",
            "Медицина",
            "Облачное хранилище"
    };

    private String[][] scientists = {
            {"Б.А. Артеменко"},
            {},
            {},
            {},
            {},
            {},
            {},
            {},
            {"Облачное хранилище"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_significant_discoveries);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        disc = findViewById(R.id.significant);

        // Подготовка данных для адаптера
        List<Map<String, String>> groupData = new ArrayList<>();
        List<List<Map<String, String>>> childData = new ArrayList<>();

        // Заполняем группы
        for (int i = 0; i < scienceGroups.length; i++) {
            Map<String, String> groupMap = new HashMap<>();
            groupMap.put("groupName", scienceGroups[i]);
            groupData.add(groupMap);
            // Заполняем детей для каждой группы
            List<Map<String, String>> childrenList = new ArrayList<>();
            for (String scientist : scientists[i]) {
                Map<String, String> childMap = new HashMap<>();
                childMap.put("scientistName", scientist);
                childrenList.add(childMap);
            }
            childData.add(childrenList);
        }

        // Создаем адаптер
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"groupName"},
                new int[]{android.R.id.text1},
                childData,
                android.R.layout.simple_list_item_1,
                new String[]{"scientistName"},
                new int[]{android.R.id.text1}
        );

        disc.setAdapter(adapter);

        // Обработчик клика по ученому
        disc.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String scientist = scientists[groupPosition][childPosition];
                openScientistLink(scientist);
                return true;
            }
        });
    }

    private void openScientistLink(String scientist) {
        String url = getUrlForScientist(scientist);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private String getUrlForScientist(String scientist) {
        switch (scientist) {
            case "Б.А. Артеменко":
            case "Интернет":
                return "https://famous-scientists.ru/anketa/artemenko-boris-aleksandrovich-13471";
            case "Я диск":
                return "https://famous-scientists.ru/anketa/artemenko-boris-aleksandrovich-13471";
            case "А.С.Гриценко":
                return "https://famous-scientists.ru/anketa/gricenko-svetlana-anatolevna-14719";
            case "А.Л.Бабаян":
                return "https://ru.hayazg.info/%D0%91%D0%B0%D0%B1%D0%B0%D1%8F%D0%BD_%D0%90%D0%BB%D0%B5%D0%BA%D1%81%D0%B0%D0%BD%D0%B4%D1%80_%D0%9B%D1%8C%D0%B2%D0%BE%D0%B2%D0%B8%D1%87";
            case "учёный 1":
                return "https://famous-scientists.ru/anketa/astashkin-vladimir-mihajlovich-9995";
            case "учёный 2":
                return "https://famous-scientists.ru/anketa/belgorodskij-evgenij-aleksandrovich-9998";
            case "учёный 3":
                return "http://chel-portal.ru/en-2173";
            case "учёный 4":
                return "https://susmu.su/universitet/istoricheskaya-spravka/istoriya-sozdaniya-vuza/zakha-rov-yuriy-mikhaylovich/";
            case "учёный 5":
                return "https://susmu.su/universitet/istoricheskaya-spravka/istoriya-sozdaniya-vuza/v-l-kovalenko/";
            case "учёный 6":
                return "http://chel-portal.ru/enc/Gun_Gennadiy_Semenovich";
            case "учёный 7":
                return "https://famous-scientists.ru/anketa/petuhov-vasilij-nikolaevich-10071";
            case "учёный 8":
                return "http://chel-portal.ru/enc/Surkin_Vyacheslav_Ivanovich";
            case "Облачное хранилище":
                return "https://disk.360.yandex.ru/d/BGup_lOQyA_ksA";
            default:
                return "https://www.google.com/search?q=" + Uri.encode(scientist);
        }
    }
}


