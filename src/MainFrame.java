import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame{
    static boolean tag = true;
    private JTextField dir_input;
    private JTextField user_input;
    private JButton search_button;
    private JButton clear_button;
    private JPanel panel;
    private JLabel file_name_label;
    private JLabel base_dir_label;
    private JLabel title_label;
    private JLabel result;
    private JProgressBar progressBar;
    static String searchRes= "";

    public MainFrame(){
        setContentPane(panel);
        setTitle("fSearch");
        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        progressBar.setVisible(false);

        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dirPath = dir_input.getText();
                String userInput = user_input.getText();
                search(dirPath,userInput);
                result.setText(searchRes);
            }
        });

        clear_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("");
                user_input.setText("");
                dir_input.setText("");
                searchRes = "";
            }
        });
    }

    static void search(String dirPath , String fname){

        searchRes = "<html>";

        File dir = new File(dirPath);
        File files[] = dir.listFiles();

        if(files == null) {
            searchRes = "File not found";
            return;
        }

        for (int i = 0 ; i < files.length;i++){
            if(files[i].getPath().equals("D:\\$RECYCLE.BIN")) continue;
            printFile(files[i],fname);
        }
        if(tag) searchRes = "File not found";
    }

    static void printFile(File file, String fname){
        if(file.isFile()){
            if(file.getName().contains(fname)){
                searchRes += file.getAbsolutePath() + "<br>";
                tag = false;
            }
        }else{
            File[] files = file.listFiles();

            if(files == null) return ;

            for (int i = 0 ; i < files.length;i++){
                printFile(files[i],fname);
            }
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}