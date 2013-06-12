package com.sitescout.ui.data;

import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import javax.inject.Named;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Assembles the information for presentation in a graph.
 * Not Yet Implemented
 *
 * @author sean
 */

@Named
public class CampaignDetailsGraph {


    public BufferedImage createGraph(HourlyEntityStatsDTO<?> campaignData) {

        final CategoryDataset dataset = createDataset(campaignData);
        final JFreeChart chart = createChart(dataset);
        BufferedImage graph = draw(chart, 1000, 1000);
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file1 = new File("chart.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 1000, 1000, info);
        } catch (IOException e) {
        }
        return graph;

    }


    public CategoryDataset createDataset(HourlyEntityStatsDTO<?> campaignData) {

        List<Integer> hours = new ArrayList<>();
        HashMap<Integer, Integer> bids = new HashMap<>();
        HashMap<Integer, Integer> wins = new HashMap<>();


        for (HourlyStatsDTO hourStats : campaignData.getStatsList()) {
            StatsDTO stats = hourStats.getStats();

            hours.add(hourStats.getHour());
            bids.put(hourStats.getHour(), stats.getImpressionsBid());
            wins.put(hourStats.getHour(), stats.getImpressionsWon());


        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Integer hour : hours) {

            dataset.addValue(wins.get(hour), "Wins", hour);
            dataset.addValue(bids.get(hour) - wins.get(hour), "Bids", hour);
        }

        return dataset;
    }

    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart stackedChart = ChartFactory.createStackedBarChart("Stacked Bar Chart", "Category", "Value",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        StackedBarRenderer renderer = new StackedBarRenderer();

        renderer.setSeriesPaint(0, Color.black);
        renderer.setSeriesPaint(1, Color.cyan);
        renderer.setItemMargin(0.10000000000000001D);
        renderer.setDrawBarOutline(false);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        //end


        stackedChart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = stackedChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);


        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        /*// set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, Color.lightGray
        );*/


        final CategoryAxis domainAxis = plot.getDomainAxis();
        CategoryLabelPositions CategoryLabelPositions;
        domainAxis.setCategoryLabelPositions(org.jfree.chart.axis.CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
        // OPTIONAL CUSTOMISATION COMPLETED.
        return stackedChart;
    }


    protected static BufferedImage draw(JFreeChart chart, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        chart.draw(g2, new Rectangle2D.Double(0, 0, width, height));
        g2.dispose();
        return img;
    }

}
