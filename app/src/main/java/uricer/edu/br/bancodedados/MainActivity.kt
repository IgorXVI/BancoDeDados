package uricer.edu.br.bancodedados

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_adicionar_aluno.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var banco = openOrCreateDatabase(
                "AlunoDB1",
                Context.MODE_PRIVATE,
                null
        )

        banco.execSQL(
            """CREATE TABLE IF NOT EXISTS aluno
                 (
                    id  INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome VARCHAR(254),
                    curso VARCHAR(254),
                    pont INTEGER
                 );

                 """
        )

        banco.close()

    }


    fun criarUsuario(view: View) {
        val intent = Intent(
                this,
                AdicionarAlunoActivity::class.java
        )

        startActivity(intent)

    }


    override fun onResume() {
        super.onResume()

        var banco = openOrCreateDatabase(
                "AlunoDB1",
                Context.MODE_PRIVATE,
                null

        )

        var alunos =
                findViewById<TableLayout>(R.id.tabela)

        alunos.removeAllViews()

        var linhas = banco.rawQuery("SELECT * FROM aluno ORDER BY pont ASC", null)

        while (linhas.moveToNext()) {
            var linha = TableRow(this)

            val id = TextView(this)
            val nome = TextView(this)
            val curso = TextView(this)
            val pont = TextView(this)

            id.gravity = Gravity.CENTER
            nome.gravity = Gravity.CENTER
            curso.gravity = Gravity.CENTER
            pont.gravity = Gravity.CENTER

            id.text = linhas.getString(0)
            nome.text = linhas.getString(1)
            curso.text = linhas.getString(2)
            pont.text = linhas.getString(3)

            linha.addView(id)
            linha.addView(nome)
            linha.addView(curso)
            linha.addView(pont)

            alunos.addView(linha)

        }
        banco.close()




    }


}
