import javax.swing.JFrame;

public class ChartDriver {
    public static void main(String[] args) {
	
	RefereeList refos = new RefereeList();
	ChartFrame frame = new ChartFrame(refos);

	final int FRAME_WIDTH = 450;
	final int FRAME_HEIGHT = 400;

	frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	frame.setTitle("ChartFrame Test");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setVisible(true);
    }
}