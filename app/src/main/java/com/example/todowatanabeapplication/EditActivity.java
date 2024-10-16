package com.example.todowatanabeapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.todowatanabeapplication.database.dao.dto.ToDoItem;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {
    private ToDoDatabase db;
    private ToDoItem toDoItem;

    private EditText editToDoTitle;
    private EditText editDetailToDo;
    private CheckBox editCompleted;
    private Button btnSelectDate;
    private TextView editSelectedDate;
    private Spinner spinnerPriority;
    private Button btnEdit;
    private String selectedDate = ""; //選択された日付を格納する変数

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit);

        //フィールドとの関連付け
        editToDoTitle = findViewById(R.id.editToDoTitle);
        editDetailToDo = findViewById(R.id.editDetailToDo);
        editCompleted = findViewById(R.id.editCompleted);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        editSelectedDate = findViewById(R.id.editSelectedDate);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        btnEdit = findViewById(R.id.btnEdit);

        //データベースの初期化
        db = Room.databaseBuilder(getApplicationContext(),
                ToDoDatabase.class, "todo_database").allowMainThreadQueries().build();

        // Spinnerの設定（重要度の選択肢をセット）
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.priority_array,  // strings.xmlに定義した選択肢を使用
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(adapter);  // Spinnerにアダプターをセット

        // 日付を選択するボタンの処理
        btnSelectDate.setOnClickListener(v -> {
            // 現在の日付を取得
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // DatePickerDialogを表示
            DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // 月は0から始まるので+1する
                        monthOfYear = monthOfYear + 1;

                        // 選択された日付を文字列に変換
                        selectedDate = year1 + "-" + String.format("%02d", monthOfYear) + "-" + String.format("%02d", dayOfMonth);

                        // TextViewに選択された日付を表示
                        editSelectedDate.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // タスクIDを取得
        int toDoId = getIntent().getIntExtra("TODO_ID", -1);

        if (toDoId != -1) {
            toDoItem = db.toDoDao().getToDoItemById(toDoId);
            if (toDoItem != null) {
                editToDoTitle.setText(toDoItem.getToDoTitle());
                editDetailToDo.setText(toDoItem.getToDoDetail());
                editCompleted.setChecked(toDoItem.getStatus());
                editSelectedDate.setText(toDoItem.getDeadLine());
                selectedDate = toDoItem.getDeadLine();
                // 取得した重要度に応じてSpinnerの選択位置を設定
                if (toDoItem.getPriority() != null) {
                    int spinnerPosition = adapter.getPosition(toDoItem.getPriority());
                    spinnerPriority.setSelection(spinnerPosition);
                }
            }
        }


        //編集ボタンをクリックしたときの処理
        btnEdit.setOnClickListener(v -> {
            // 入力された情報を取得
            String toDoTitle = editToDoTitle.getText().toString();
            String toDoDetaile = editDetailToDo.getText().toString();
            String deadline = selectedDate;
            String priority = spinnerPriority.getSelectedItem().toString(); // Spinnerで選ばれた重要度
            boolean isComplete = editCompleted.isChecked();

                toDoItem.setToDoTitle(toDoTitle);
                toDoItem.setToDoDetail(toDoDetaile);
                toDoItem.setDeadLine(deadline);
                toDoItem.setPriority(priority);
                toDoItem.setStatus(isComplete);

            //更新
            db.toDoDao().update(toDoItem);
            finish();
        });

    }
}
