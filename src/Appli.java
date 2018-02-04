import Controller.ControlGroup;

class Appli {

  public static void main(String[] args) {

     javax.swing.SwingUtilities.invokeLater( new Runnable() {

      public void run() {
          ControlGroup controller = new ControlGroup();
      }
    });
  }
}
