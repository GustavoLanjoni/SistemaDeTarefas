package Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GerenciadorDeTarefas {
    private JFrame frame;
    private DefaultListModel<Tarefa> modeloTarefas; // Corrigido para Tarefa
    private JList<Tarefa> listaTarefas;
    private ArrayList<Tarefa> tarefas;

    public GerenciadorDeTarefas() {
        tarefas = new ArrayList<>();
        modeloTarefas = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloTarefas);

        frame = new JFrame("Gerenciador de Tarefas");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnEditar = new JButton("Editar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);

        frame.add(new JScrollPane(listaTarefas), BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        btnAdicionar.addActionListener(e -> adicionarTarefa());
        btnEditar.addActionListener(e -> editarTarefa());
        btnRemover.addActionListener(e -> removerTarefa());

        frame.setVisible(true);
    }

    private void adicionarTarefa() {
        String titulo = JOptionPane.showInputDialog(frame, "Título da Tarefa:");
        if (titulo != null && !titulo.trim().isEmpty()) {
            Tarefa novaTarefa = new Tarefa(titulo, "Descrição não definida", "Pendente");
            tarefas.add(novaTarefa);
            modeloTarefas.addElement(novaTarefa);
        }
    }

    private void editarTarefa() {
        Tarefa selecionada = listaTarefas.getSelectedValue();
        if (selecionada != null) {
            String novoTitulo = JOptionPane.showInputDialog(frame, "Editar Título:", selecionada.getTitulo());
            if (novoTitulo != null && !novoTitulo.trim().isEmpty()) {
                selecionada.setTitulo(novoTitulo);
                listaTarefas.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para editar.");
        }
    }

    private void removerTarefa() {
        Tarefa selecionada = listaTarefas.getSelectedValue();
        if (selecionada != null) {
            tarefas.remove(selecionada);
            modeloTarefas.removeElement(selecionada);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para remover.");
        }
    }

    public static void main(String[] args) {
        new GerenciadorDeTarefas(); // Corrigido para usar o nome correto
    }
}

// Classe Tarefa (Adicione-a no mesmo arquivo ou em um arquivo separado)
class Tarefa {
    private String titulo;
    private String descricao;
    private String status;

    public Tarefa(String titulo, String descricao, String status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return titulo + " (" + status + ")";
    }
}
