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

public class Significant_discoveries extends AppCompatActivity {

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

    // Ученые по группам
    private String[][] scientists = {
            {"И.В. Курчатов", "С.В. Вонсовский", "Е.Н. Аврорин", "В.Ф. Бухтояров", "В.И. Суркин", "В.М. Асташкин", "Научные работы по физике"},
            {"Е.А. Белгородский", "В.Н. Петухов", "Научные работы по математике"},
            {"М.А. Андреева", "Г.С. Гун"},
            {"Ю.М. Захаров", "В.Л. Коваленко"},
            {},
            {"Г.П. Вяткин"},
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
            case "И.В. Курчатов":
                return "https://scientificrussia.ru/articles/iv-kurcatov-istoria-uspeha-atomnogo-proekta";
            case "С.В. Вонсовский":
                return "https://warheroes.ru/hero/hero.asp?Hero_id=19731";
            case "Е.Н. Аврорин":
                return "https://www.biblioatom.ru/persons/avrorin_evgeniy_nikolaevich/";
            case "М.А. Андреева":
                return "https://www.cspu.ru/news/veterany-yuurggpu-mariya-andreeva";
            case "В.М. Асташкин":
                return "https://famous-scientists.ru/anketa/astashkin-vladimir-mihajlovich-9995";
            case "Е.А. Белгородский":
                return "https://famous-scientists.ru/anketa/belgorodskij-evgenij-aleksandrovich-9998";
            case "В.Ф. Бухтояров":
                return "http://chel-portal.ru/en-2173";
            case "Ю.М. Захаров":
                return "https://susmu.su/universitet/istoricheskaya-spravka/istoriya-sozdaniya-vuza/zakha-rov-yuriy-mikhaylovich/";
            case "В.Л. Коваленко":
                return "https://susmu.su/universitet/istoricheskaya-spravka/istoriya-sozdaniya-vuza/v-l-kovalenko/";
            case "Г.С. Гун":
                return "http://chel-portal.ru/enc/Gun_Gennadiy_Semenovich";
            case "В.Н. Петухов":
                return "https://famous-scientists.ru/anketa/petuhov-vasilij-nikolaevich-10071";
            case "В.И. Суркин":
                return "http://chel-portal.ru/enc/Surkin_Vyacheslav_Ivanovich";
            case "Г.П. Вяткин":
                return "https://www.susu.ru/ru/professorskiy-zal/german-platonovich-vyatkin";
            case "Научные работы по физике":
                return "https://disk.360.yandex.ru/d/FQSHeDzec4ppiA";
            case "Научные работы по математике":
                return "https://disk.360.yandex.ru/d/r26BL7oC-_6ztA";
            case "Облачное хранилище":
                return "https://disk.360.yandex.ru/d/BGup_lOQyA_ksA";
            default:
                return "https://www.google.com/search?q=" + Uri.encode(scientist);
        }
    }
}
