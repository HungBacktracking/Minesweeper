package com.example.caculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class MinesweeperActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper);

        Button btn = (Button) findViewById(R.id.startButton);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int rows = getNumberFromEditText(R.id.editNumRows);
        int cols = getNumberFromEditText(R.id.editNumCols);
        
        generateGame(rows, cols);
    }

    private View.OnClickListener mine = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView cell = (TextView) v;
            String type = cell.getTag().toString();
            if (type == "bomb")
            {
                cell.setBackgroundResource(R.drawable.bomb);
                endGame();
            }
            else if (type == "zero") cell.setBackgroundResource(R.drawable.empty);
            else if (type == "one") cell.setBackgroundResource(R.drawable.one);
            else if (type == "two") cell.setBackgroundResource(R.drawable.two);
            else if (type == "three") cell.setBackgroundResource(R.drawable.three);
            else if (type == "four") cell.setBackgroundResource(R.drawable.four);
            else if (type == "five") cell.setBackgroundResource(R.drawable.five);
            else if (type == "six") cell.setBackgroundResource(R.drawable.six);
            else if (type == "seven") cell.setBackgroundResource(R.drawable.seven);
            else if (type == "eight") cell.setBackgroundResource(R.drawable.eight);

            cell.setOnClickListener(null);
            cell.setOnLongClickListener(null);
        }
    };

    private View.OnLongClickListener flag = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            TextView cell = (TextView) v;
            cell.setBackgroundResource(R.drawable.flag);
            cell.setOnClickListener(null);
            cell.setOnLongClickListener(null);
            return false;
        }
    };

    private void endGame() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridMain);

        int childCount = gridLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView childView = (TextView) gridLayout.getChildAt(i);
            childView.setOnClickListener(null);
            childView.setOnLongClickListener(null);
        }

        // Khai báo một Context cho AlertDialog
        final Context context = this;

        // Khởi tạo một AlertDialog.Builder
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // Thiết lập tiêu đề và nội dung cho thông báo
        alertDialogBuilder.setTitle("Let's try again :<");
        alertDialogBuilder.setMessage("You lose!!!");

        // Thiết lập nút OK để đóng thông báo
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Khi người dùng nhấn nút OK, đóng thông báo
                dialog.dismiss();
            }
        });

        // Tạo và hiển thị AlertDialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void generateGame(int rows, int cols) {
        GameData gameData = new GameData(rows, cols);
        loadGame(gameData);
    }

    private int nextAvailableId = 65000;
    private void loadGame(GameData gameData) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridMain);
        gridLayout.removeAllViews();

        int numberOfRows = gameData.getNumberOfRows();
        gridLayout.setRowCount(numberOfRows);
        int numberOfColumns = gameData.getNumberOfColumns();
        gridLayout.setColumnCount(numberOfColumns);

        String data;
        TextView cell;
        for (int i = 0; i < numberOfRows; i++)
        {
            for (int j = 0; j < numberOfColumns; j++)
            {
                cell = getNewCell(i, j);
                data = gameData.getDataAtCell(i, j);
                cell.setTag(data);
                gridLayout.addView(cell);
                cell.setOnClickListener(mine);
                cell.setOnLongClickListener(flag);
            }
        }
        gridLayout.setMinimumHeight(gridLayout.getWidth() / numberOfColumns * numberOfRows);
    }

    private TextView getNewCell(int i, int j) {
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();

        TextView cell = new TextView(this);
        cell.setId(nextAvailableId++);

        layoutParams.rowSpec = GridLayout.spec(i, 1, 1f);
        layoutParams.columnSpec = GridLayout.spec(j, 1,  1f);
        layoutParams.setMargins(0, 0, 0, 0);
        cell.setPadding(0,0,0,0);
        cell.setLayoutParams(layoutParams);
        cell.setBackgroundResource(R.drawable.unknown);

        return cell;
    }

    private int getNumberFromEditText(int idEdit) {
        EditText editText = (EditText) findViewById(idEdit);
        String Text = editText.getText().toString();
        int val = Integer.valueOf(Text);
        return val;
    }
}