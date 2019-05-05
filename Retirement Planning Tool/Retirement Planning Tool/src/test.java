
	import javax.swing.JFrame;  
	import javax.swing.SwingUtilities;
	import javax.swing.WindowConstants;

	import org.jfree.chart.ChartFactory;  
	import org.jfree.chart.ChartPanel;  
	import org.jfree.chart.JFreeChart;  
	import org.jfree.chart.plot.PlotOrientation;  
	import org.jfree.data.category.CategoryDataset;  
	import org.jfree.data.category.DefaultCategoryDataset; 
	public class test extends JFrame{

		 private static final long serialVersionUID = 1L;  
		  
		  public test(String appTitle) {  
		    super(appTitle);  
		  
		    // Create Dataset  
		    CategoryDataset dataset = createDataset();  
		      
		    //Create chart  
		    JFreeChart chart=ChartFactory.createBarChart(  
		        "Retirement", //Chart Title  
		        "Age", // Category axis  
		        "Amount in Dollars", // Value axis  
		        dataset,  
		        PlotOrientation.VERTICAL,  
		        true,true,false  
		       );  
		  
		    ChartPanel panel=new ChartPanel(chart);  
		    setContentPane(panel);  
		  }  
		  
		  private CategoryDataset createDataset() {  
		    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		  
		    // age 60 
		    dataset.addValue(10, "spending", "age 60");  
		    dataset.addValue(20, "income", "age 60");  
		    dataset.addValue(400, "net savings", "age 60");  
		  
		    // age 70  
		    dataset.addValue(10, "spending", "age 70");  
		    dataset.addValue(20, "income", "age 70");  
		    dataset.addValue(440, "net savings", "age 70");  
		  
		    // age 80
		    dataset.addValue(10, "spending", "age 80");  
		    dataset.addValue(20, "income", "age 80");  
		    dataset.addValue(460, "net savings", "age 80");  
		  
		    return dataset;  
		  }  
		  
		  public static void main(String[] args) throws Exception {  
		    SwingUtilities.invokeAndWait(()->{  
		    	test example=new test("Bar Chart Window");  
		      example.setSize(800, 400);  
		      example.setLocationRelativeTo(null);  
		      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		      example.setVisible(true);  
		    });  
		  }  
}
