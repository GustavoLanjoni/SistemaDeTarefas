package Sistema;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GerenciadorDeTarefas {
    private JFrame frame;
    private DefaultListModel<Tarefa> modeloTarefas;
    private JList<Tarefa> listaTarefas;
    private ArrayList<Tarefa> tarefas;
    private JLabel lblRelogio;

    public GerenciadorDeTarefas() {
        tarefas = new ArrayList<>();
        modeloTarefas = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloTarefas);

        frame = new JFrame("Gerenciador de Tarefas");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Relógio em tempo real
        lblRelogio = new JLabel();
        lblRelogio.setFont(new Font("Arial", Font.BOLD, 16));
        lblRelogio.setHorizontalAlignment(SwingConstants.CENTER);
        atualizarRelogio();

        // Botões
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnEditar = new JButton("Editar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);

        // Adicionando componentes à janela
        frame.add(lblRelogio, BorderLayout.NORTH); // Relógio na parte superior
        frame.add(new JScrollPane(listaTarefas), BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        btnAdicionar.addActionListener(e -> adicionarTarefa());
        btnEditar.addActionListener(e -> editarTarefa());
        btnRemover.addActionListener(e -> removerTarefa());

        frame.setVisible(true);

        // Iniciar o relógio em tempo real
        iniciarTimerRelogio();
    }

    private void adicionarTarefa() {
        String titulo = JOptionPane.showInputDialog(frame, "Título da Tarefa:");
        if (titulo != null && !titulo.trim().isEmpty()) {
            String descricao = JOptionPane.showInputDialog(frame, "Descrição da Tarefa:");
            Tarefa novaTarefa = new Tarefa(titulo, descricao != null ? descricao : "Descrição não definida", "Pendente");
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

    private void atualizarRelogio() {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        lblRelogio.setText("Hora atual: " + formatoHora.format(new Date()));
    }

    private void iniciarTimerRelogio() {
        Timer timer = new Timer(1000, e -> atualizarRelogio());
        timer.start();
    }

    public static void main(String[] args) {
        new GerenciadorDeTarefas();
    }
}

// Classe Tarefa
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
        return titulo + " - " + status;
    }
}
