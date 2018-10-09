package uricer.edu.br.bancodedados

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class AdicionarAlunoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_aluno)
    }

    fun salvar (botao : View) {
        var etNome = findViewById<EditText>(R.id.nome)
        var etCurso = findViewById<EditText>(R.id.curso)
        var etPont = findViewById<EditText>(R.id.pontuacao)

        val nome = etNome.text.toString()
        val curso = etCurso.text.toString()
        val pont = etPont.text.toString().toInt()

        var banco = openOrCreateDatabase(
                "AlunoDB1",
                Context.MODE_PRIVATE,
                null
        )

        banco.execSQL("""
            INSERT INTO aluno ('nome', 'curso', 'pont')
            VALUES ('$nome', '$curso', '$pont')
        """)

        banco.close()

        finish()



    }
}
