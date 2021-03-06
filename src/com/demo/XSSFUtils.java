/**
 * 
 */
package com.demo;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;

import com.desay.utils.PoiUtils;

/**
* @author ?????????  E-mail: 470029260@qq.com
* @version ???????????????2020???8???21??? ??????10:14:08
* ?????????
*/
public class XSSFUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 //??????excel?????????
		XSSFWorkbook workbook = new XSSFWorkbook();
        //?????????????????????sheet
		XSSFSheet sheetResult = workbook.createSheet("SVMonkeyResult");
		testForLineChart(sheetResult);
		try {
			PoiUtils.outputXlsxToFile(workbook, "D:\\xssfUtils.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void testForLineChart(XSSFSheet sheet) {
        Map<String, Object> arams = new HashMap<String, Object>();

        // ???????????????B36????????????AA53??????????????????????????????0
        int[] chartPositon = new int[] {getColumnIndexByAddress("B"), 35, getColumnIndexByAddress("AA"), 52, 0, 0, 0, 0};

        // ??????x??????????????????B60???B90????????????Day??????
        int[] xAxisRange = new int[] {59, 89, XSSFUtils.getColumnIndexByAddress("B"), XSSFUtils.getColumnIndexByAddress("B")};

        // ????????????????????????????????????????????????D60???D90???J60???J90???P60???P90???
        for (int i = 0; i < 90; i++) {
        	Row row = sheet.createRow(i);
			Cell cellD = row.createCell(3);
			cellD.setCellValue(i*5);
			
			Cell cellJ = row.createCell(9);//0A 1B 2C 3D 4e 5f 6g 7h 8i 9j 10k 11l 12m 13n 14o 15p 16q 17r 
			cellJ.setCellValue(i*5 + 10);
			
			Cell cellP = row.createCell(15);
			cellP.setCellValue(i*5 + 50);
		}
        // ???????????????D59???J59???P59???
        List<int[]> seriesRangeList = new ArrayList<int[]>();
        seriesRangeList.add(new int[] {
                58, 58, XSSFUtils.getColumnIndexByAddress("D"), XSSFUtils.getColumnIndexByAddress("D"),
                59, 89, XSSFUtils.getColumnIndexByAddress("D"), XSSFUtils.getColumnIndexByAddress("D")});
        seriesRangeList.add(new int[] {
                58, 58, XSSFUtils.getColumnIndexByAddress("J"), XSSFUtils.getColumnIndexByAddress("J"),
                59, 89, XSSFUtils.getColumnIndexByAddress("J"), XSSFUtils.getColumnIndexByAddress("J")});
        seriesRangeList.add(new int[] {
                58, 58, XSSFUtils.getColumnIndexByAddress("P"), XSSFUtils.getColumnIndexByAddress("P"),
                59, 89, XSSFUtils.getColumnIndexByAddress("P"), XSSFUtils.getColumnIndexByAddress("P")});

        arams.put("chartPosition", chartPositon);
        arams.put("chartTitle", "");
        arams.put("dispBlanksAs", "zero");
        arams.put("legendPosition", "t");
        arams.put("xAxisDataCellRange", xAxisRange);
        arams.put("seriesDataCellRangeList", seriesRangeList);
        arams.put("lineColor", null);
        arams.put("isXAxisDelete", false);
        arams.put("yAxisPosition", "l");

        createLineChart(sheet, arams);
    }

    public static void testForBarChart(XSSFSheet sheet) {
        Map<String, Object> params = new HashMap<String, Object>();

        // ???????????????G20????????????????????????50???EMU point???N39????????????????????????50???EMU point???
        int[] chartPositon = new int[] {XSSFUtils.getColumnIndexByAddress("G"), 19, XSSFUtils.getColumnIndexByAddress("N"), 38, 50, 0, 50, 0};

        // ????????????(A9)
        String chartTitle = sheet.getRow(8).getCell(XSSFUtils.getColumnIndexByAddress("A")).getStringCellValue();

        // ??????x??????????????????C8???N8??????????????????????????????
        int[] xAxisRange = new int[] {7, 7, XSSFUtils.getColumnIndexByAddress("C"), XSSFUtils.getColumnIndexByAddress("N")};

        // ????????????????????????????????????????????????C11:N11???
        // ???????????????B11???
        List<int[]> seriesRangeList = new ArrayList<int[]>();
        seriesRangeList.add(
                new int[] {
                        10, 10, XSSFUtils.getColumnIndexByAddress("B"), XSSFUtils.getColumnIndexByAddress("B"),
                        10, 10, XSSFUtils.getColumnIndexByAddress("C"), XSSFUtils.getColumnIndexByAddress("N")});

        params.put("chartPosition", chartPositon);
        params.put("chartTitle", chartTitle);
        params.put("dispBlanksAs", "gap");
        params.put("legendPosition", "r");
        params.put("xAxisDataCellRange", xAxisRange);
        params.put("seriesDataCellRangeList", seriesRangeList);
        params.put("barColor", new XSSFColor(new Color(247, 150, 70)));

        createBarChart(sheet, params);
    }

    public static void testForComboChart(XSSFSheet sheet) {
        Map<String, Object> params = new HashMap<String, Object>();

        // ???????????????A20????????????????????????10???EMU point???G39????????????????????????30???EMU point???
        int[] chartPosition =
                new int[] {XSSFUtils.getColumnIndexByAddress("A"), 19, XSSFUtils.getColumnIndexByAddress("G"), 38, 10, 0, 30, 0};

        // ????????????(A9)
        String chartTitle = sheet.getRow(8).getCell(XSSFUtils.getColumnIndexByAddress("A")).getStringCellValue();

        // ??????x??????????????????C8???N8??????????????????????????????
        int[] xAxisRange = new int[] {7, 7, XSSFUtils.getColumnIndexByAddress("C"), XSSFUtils.getColumnIndexByAddress("N")};

        // ?????????????????????????????????????????????????????????C9:N9???
        // ???????????????B9???
        List<int[]> barSeriesRangeList = new ArrayList<int[]>();
        barSeriesRangeList.add(
                new int[] {
                        8, 8, XSSFUtils.getColumnIndexByAddress("B"), XSSFUtils.getColumnIndexByAddress("B"),
                        8, 8, XSSFUtils.getColumnIndexByAddress("C"), XSSFUtils.getColumnIndexByAddress("N")});

        // ?????????????????????????????????????????????????????????C10:N10???
        // ???????????????B10???
        List<int[]> lineSeriesRangeList = new ArrayList<int[]>();
        lineSeriesRangeList.add(
                new int[] {
                        9, 9, XSSFUtils.getColumnIndexByAddress("B"), XSSFUtils.getColumnIndexByAddress("B"),
                        9, 9, XSSFUtils.getColumnIndexByAddress("C"), XSSFUtils.getColumnIndexByAddress("N")});

        params.put("chartPosition", chartPosition);
        params.put("chartTitle", chartTitle);
        params.put("dispBlanksAs", "gap");
        params.put("legendPosition", "r");
        params.put("barXAxisDataCellRange", xAxisRange);
        params.put("barSeriesDataCellRangeList", barSeriesRangeList);
        params.put("barColor", new XSSFColor(new Color(79, 129, 189)));
        params.put("lineXAxisDataCellRange", xAxisRange);
        params.put("lineSeriesDataCellRangeList", lineSeriesRangeList);
        params.put("lineColor", new XSSFColor(new Color(190, 75, 72)));
        params.put("lineIsXAxisDelete", true);
        params.put("lineYAxisPosition", "r");

        XSSFUtils.createComboChart(sheet, params);
    }

    /**
     * create line chart
     * @param sheet
     * @param params
     *             chartPosition              int[]{startCol, startRow, endCol, endRow,
     *                                              xOffsetInStartCell, yOffsetInStartCell,
     *                                              xOffsetInEndCell, yOffsetInEndCell}
     *             chartTitle                 String
     *             xAxisDataCellRange         int[]{startRow, endRow, startCol, endCol}
     *             seriesDataCellRangeList    List<int[]{
     *                                            legendStartRow, legendEndRow, legendStartCol, legendEndCol,
     *                                            startRow, endRow, startCol, endCol}>
     *             lineColor                  XSSFColor
     */
    public static void createLineChart(XSSFSheet sheet, Map<String, Object> params) {

        // ???????????????
        CTPlotArea ctPlotArea = createCTPlotArea(sheet, params);

        // ????????????
        createCTLineChart(sheet, ctPlotArea, params);
    }

    /**
     * create bar chart
     * @param sheet
     * @param params
     *             chartPosition              int[]{startCol, startRow, endCol, endRow,
     *                                              xOffsetInStartCell, yOffsetInStartCell,
     *                                              xOffsetInEndCell, yOffsetInEndCell}
     *             chartTitle                 String
     *             xAxisDataCellRange         int[]{startRow, endRow, startCol, endCol}
     *             seriesDataCellRangeList    List<int[]{
     *                                             legendStartRow, legendEndRow, legendStartCol, legendEndCol,
     *                                             startRow, endRow, startCol, endCol}>
     *             barColor                   XSSFColor
     */
    public static void createBarChart(XSSFSheet sheet, Map<String, Object> params) {

        // ???????????????
        CTPlotArea ctPlotArea = createCTPlotArea(sheet, params);

        // ????????????
        createCTBarChart(sheet, ctPlotArea, params);
    }

    /**
     * create combination chart
     * @param sheet
     * @param params
     *         chartPosition                   int[]{startCol, startRow, endCol, endRow,
     *                                                 xOffsetInStartCell, yOffsetInStartCell,
     *                                                 xOffsetInEndCell, yOffsetInEndCell}
     *         chartTitle                      String
     *         barXAxisDataCellRange           int[]{startRow, endRow, startCol, endCol}
     *         barSeriesDataCellRangeList      List<int[]{
     *                                                 legendStartRow, legendEndRow, legendStartCol, legendEndCol,
     *                                                 startRow, endRow, startCol, endCol}>
     *         barColor                       XSSFColor
     *         lineXAxisDataCellRange          int[]{startRow, endRow, startCol, endCol}
     *         lineSeriesDataCellRangeList     List<int[]{
     *                                                 legendStartRow, legendEndRow, legendStartCol, legendEndCol,
     *                                                 startRow, endRow, startCol, endCol}>
     *         lineColor                       XSSFColor
     *         lineIsXAxisDelete               Boolean
     *         lineYAxisPosition               String
     */
    public static void createComboChart(XSSFSheet sheet, Map<String, Object> params) {

        Map<String, Object> barParams = new HashMap<String, Object>();
        Map<String, Object> lineParams = new HashMap<String, Object>();
        barParams.put("xAxisDataCellRange", params.get("barXAxisDataCellRange"));
        barParams.put("seriesDataCellRangeList", params.get("barSeriesDataCellRangeList"));
        barParams.put("barColor", params.get("barColor"));
        lineParams.put("xAxisDataCellRange", params.get("lineXAxisDataCellRange"));
        lineParams.put("seriesDataCellRangeList", params.get("lineSeriesDataCellRangeList"));
        lineParams.put("lineColor", params.get("lineColor"));
        lineParams.put("isXAxisDelete", params.get("lineIsXAxisDelete"));
        lineParams.put("yAxisPosition", params.get("lineYAxisPosition"));

        // ???????????????
        CTPlotArea ctPlotArea = createCTPlotArea(sheet, params);

        // ????????????
        createCTBarChart(sheet, ctPlotArea, barParams);
        createCTLineChart(sheet, ctPlotArea, lineParams);
    }

    /**
     * create line chart
     * @param params
     *              xAxisDataCellRange           int[]{startRow, endRow, startCol, endCol}
     *              seriesDataCellRangeList      List<int[]{
     *                                                legendStartRow, legendEndRow, legendStartCol, legendEndCol,
     *                                                startRow, endRow, startCol, endCol}>
     *              lineColor                    XSSFColor
     *              isXAxisDelete                Boolean
     *              yAxisPosition                String
     */
    @SuppressWarnings("unchecked")
	private static void createCTLineChart(XSSFSheet sheet, CTPlotArea ctPlotArea, Map<String, Object> params) {

        int[] xAxisDataCellRange = (int[]) params.get("xAxisDataCellRange");
        List<int[]> seriesDataCellRangeList = (List<int[]>) params.get("seriesDataCellRangeList");
        XSSFColor lineColor = (XSSFColor) params.get("lineColor");
        Boolean isXAxisDelete = (Boolean) params.get("isXAxisDelete");
        String yAxisPosition = (String) params.get("yAxisPosition");

        CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
        ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);
        ctLineChart.addNewVaryColors().setVal(true);

        // ??????????????????
        for (int i = 0; i <= seriesDataCellRangeList.size() - 1; i++) {
            int[] seriesDataCellRange = seriesDataCellRangeList.get(i);

            CTLineSer ctLineSer = ctLineChart.addNewSer();

            // ?????????????????????
            if (lineColor != null) {
                ctLineSer.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(lineColor.getRGB());
            }

            CTSerTx ctSerTx = ctLineSer.addNewTx();

            // ????????????
            CTStrRef ctStrRef = ctSerTx.addNewStrRef();
            ctStrRef.setF(new CellRangeAddress(
                    seriesDataCellRange[0], seriesDataCellRange[1], seriesDataCellRange[2], seriesDataCellRange[3])
                    .formatAsString(sheet.getSheetName(), true));
            ctLineSer.addNewIdx().setVal(i + 1);

            // ??????x????????????
            CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
            ctStrRef = cttAxDataSource.addNewStrRef();
            ctStrRef.setF(new CellRangeAddress(
                    xAxisDataCellRange[0], xAxisDataCellRange[1], xAxisDataCellRange[2], xAxisDataCellRange[3])
                    .formatAsString(sheet.getSheetName(), true));

            // ?????????????????????????????????????????????
            CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
            ctNumRef.setF(new CellRangeAddress(
                    seriesDataCellRange[4], seriesDataCellRange[5], seriesDataCellRange[6], seriesDataCellRange[7])
                    .formatAsString(sheet.getSheetName(), true));

            // ??????????????????
            CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
            addNewSmooth.setVal(false);

            CTMarker addNewMarker = ctLineSer.addNewMarker();
            CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
            addNewSymbol.setVal(STMarkerStyle.NONE);
        }

        int xAxisId = seriesDataCellRangeList.size() + 1 + 10000;
        int yAxisId = seriesDataCellRangeList.size() + 2 + 10000;

        ctLineChart.addNewAxId().setVal(xAxisId);
        ctLineChart.addNewAxId().setVal(yAxisId);

        // ??????x?????????
        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(xAxisId);
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewDelete().setVal(isXAxisDelete);// ????????????x???
        ctCatAx.addNewAxPos().setVal(STAxPos.B);// x???????????????????????????
        ctCatAx.addNewMajorTickMark().setVal(STTickMark.OUT);// ????????????????????????????????????????????????????????????
        ctCatAx.addNewMinorTickMark().setVal(STTickMark.NONE);// ????????????????????????????????????????????????????????????
        ctCatAx.addNewAuto().setVal(true);
        ctCatAx.addNewLblAlgn().setVal(STLblAlgn.CTR);
        ctCatAx.addNewCrossAx().setVal(yAxisId);
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);// ??????????????????????????????????????????????????????????????????
        ctCatAx.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(
                new XSSFColor(new Color(134, 134, 134)).getRGB());// x?????????

        // ??????y?????????
        CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(yAxisId);
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctValAx.addNewDelete().setVal(false);// ????????????x???
        switch (yAxisPosition) {// y???????????????????????????
            case "l" :
                ctValAx.addNewAxPos().setVal(STAxPos.L);
                ctValAx.addNewCrosses().setVal(STCrosses.MIN);// ??????????????????????????????????????????0?????????????????????????????????????????????????????????????????????MAX?????????????????????????????????
                break;
            case "r" :
                ctValAx.addNewAxPos().setVal(STAxPos.R);
                ctValAx.addNewCrosses().setVal(STCrosses.MAX);
                break;
            case "t" :
                ctValAx.addNewAxPos().setVal(STAxPos.T);
                break;
            case "b" :
                ctValAx.addNewAxPos().setVal(STAxPos.B);
                break;
            default :
                ctValAx.addNewAxPos().setVal(STAxPos.L);
                ctValAx.addNewCrosses().setVal(STCrosses.MIN);
                break;
        }
        ctValAx.addNewMajorTickMark().setVal(STTickMark.OUT);// ????????????????????????????????????????????????????????????
        ctValAx.addNewMinorTickMark().setVal(STTickMark.NONE);// ????????????????????????????????????????????????????????????
        ctValAx.addNewCrossAx().setVal(xAxisId);
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);// ??????????????????????????????????????????????????????????????????
        ctValAx.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(
                new XSSFColor(new Color(134, 134, 134)).getRGB());// y?????????
        ctValAx.addNewMajorGridlines().addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(
                new XSSFColor(new Color(134, 134, 134)).getRGB());// ???????????????????????????????????????
    }

    /**
     * create bar chart
     * @param params
     *              xAxisDataCellRange           int[]{startRow, endRow, startCol, endCol}
     *              seriesDataCellRangeList      List<int[]{
     *                                                legendStartRow, legendEndRow, legendStartCol, legendEndCol,
     *                                                startRow, endRow, startCol, endCol}>
     *              barColor                     XSSFColor
     */
    private static void createCTBarChart(XSSFSheet sheet, CTPlotArea ctPlotArea, Map<String, Object> params) {

        int[] xAxisDataCellRange = (int[]) params.get("xAxisDataCellRange");
        List<int[]> seriesDataCellRangeList = (List<int[]>) params.get("seriesDataCellRangeList");
        XSSFColor barColor = (XSSFColor) params.get("barColor");

        CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
        CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
        ctBarChart.getVaryColors().setVal(true);
        ctBarChart.addNewGrouping().setVal(STBarGrouping.CLUSTERED);
        ctBarChart.addNewBarDir().setVal(STBarDir.COL);

        for (int i = 0; i <= seriesDataCellRangeList.size() - 1; i++) {
            int[] seriesDataCellRange = seriesDataCellRangeList.get(i);

            CTBarSer ctBarSer = ctBarChart.addNewSer();

            // set bar color
            if (barColor != null) {
                ctBarSer.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(barColor.getRGB());
            }

            CTSerTx ctSerTx = ctBarSer.addNewTx();

            // legend
            CTStrRef ctStrRef = ctSerTx.addNewStrRef();
            ctStrRef.setF(new CellRangeAddress(
                    seriesDataCellRange[0], seriesDataCellRange[1], seriesDataCellRange[2], seriesDataCellRange[3])
                    .formatAsString(sheet.getSheetName(), true));
            ctBarSer.addNewIdx().setVal(i + 1);

            // x
            CTAxDataSource ctAxDataSource = ctBarSer.addNewCat();
            ctStrRef = ctAxDataSource.addNewStrRef();
            ctStrRef.setF(new CellRangeAddress(
                    xAxisDataCellRange[0], xAxisDataCellRange[1], xAxisDataCellRange[2], xAxisDataCellRange[3])
                    .formatAsString(sheet.getSheetName(), true));

            // y
            CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
            ctNumRef.setF(new CellRangeAddress(
                    seriesDataCellRange[4], seriesDataCellRange[5], seriesDataCellRange[6], seriesDataCellRange[7])
                    .formatAsString(sheet.getSheetName(), true));

            ctBarSer.addNewInvertIfNegative().setVal(false);

            ctBoolean.setVal(false);
            CTDLbls newDLbls = ctBarSer.addNewDLbls();
            newDLbls.setShowLegendKey(ctBoolean);
            newDLbls.setShowVal(ctBoolean);
            newDLbls.setShowCatName(ctBoolean);
            newDLbls.setShowSerName(ctBoolean);
            newDLbls.setShowPercent(ctBoolean);
            newDLbls.setShowBubbleSize(ctBoolean);
            newDLbls.setShowLeaderLines(ctBoolean);
        }

        int xAxisId = seriesDataCellRangeList.size() + 1;
        int yAxisId = seriesDataCellRangeList.size() + 2;

        ctBarChart.addNewAxId().setVal(xAxisId);
        ctBarChart.addNewAxId().setVal(yAxisId);

        // ??????x?????????
        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(xAxisId);
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewDelete().setVal(false);// ????????????x???
        ctCatAx.addNewAxPos().setVal(STAxPos.B);// x???????????????????????????
        ctCatAx.addNewMajorTickMark().setVal(STTickMark.OUT);// ????????????????????????????????????????????????????????????
        ctCatAx.addNewMinorTickMark().setVal(STTickMark.NONE);// ????????????????????????????????????????????????????????????
        ctCatAx.addNewCrossAx().setVal(yAxisId);
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);// ??????????????????????????????????????????????????????????????????
        ctCatAx.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(
                new XSSFColor(new Color(134, 134, 134)).getRGB());// x?????????

        // ??????y?????????
        CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(yAxisId);
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctValAx.addNewDelete().setVal(false);// ????????????y???
        ctValAx.addNewAxPos().setVal(STAxPos.L);// y???????????????????????????
        ctValAx.addNewMajorTickMark().setVal(STTickMark.OUT);// ????????????????????????????????????????????????????????????
        ctValAx.addNewMinorTickMark().setVal(STTickMark.NONE);// ????????????????????????????????????????????????????????????
        ctValAx.addNewCrossAx().setVal(xAxisId);
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);// ??????????????????????????????????????????????????????????????????
        ctValAx.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(
                new XSSFColor(new Color(134, 134, 134)).getRGB());// x?????????
        ctValAx.addNewMajorGridlines().addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(
                new XSSFColor(new Color(134, 134, 134)).getRGB());// ???????????????????????????????????????
    }

    /**
     * create CTPlotArea
     * @param position          int[]{startCol, startRow, endCol, endRow,
     *                               xOffsetInStartCell, yOffsetInStartCell, xOffsetInEndCell, yOffsetInEndCell}
     */
    private static CTPlotArea createCTPlotArea(XSSFSheet sheet, Map<String, Object> params) {

        int[] chartPosition = (int[]) params.get("chartPosition");
        String chartTitle = (String) params.get("chartTitle");
        String dispBlanksAs = (String) params.get("dispBlanksAs");
        String legendPosition = (String) params.get("legendPosition");

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor =
                drawing.createAnchor(
                        chartPosition[4] * Units.EMU_PER_POINT, chartPosition[5] * Units.EMU_PER_POINT,
                        chartPosition[6] * Units.EMU_PER_POINT, chartPosition[7] * Units.EMU_PER_POINT,
                        chartPosition[0], chartPosition[1], chartPosition[2], chartPosition[3]);
        XSSFChart chart = drawing.createChart(anchor);
        CTChart ctChart = chart.getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();

        // set chart title
        chart.setTitleText(chartTitle);
        ctChart.getTitle().addNewOverlay().setVal(false);// ???????????????????????????????????????????????????

        ctChart.addNewShowDLblsOverMax().setVal(true);

        switch (dispBlanksAs) {
            case "span" :
                ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.SPAN);
                break;
            case "gap" :
                ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.GAP);
                break;
            case "zero" :
                ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
                break;
            default:
                ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
                break;
        }

        // legend
        CTLegend ctLegend = ctChart.addNewLegend();
        ctLegend.addNewOverlay().setVal(false);

        switch (legendPosition) {
            case "b" :
                ctLegend.addNewLegendPos().setVal(STLegendPos.B);
                break;
            case "tr" :
                ctLegend.addNewLegendPos().setVal(STLegendPos.TR);
                break;
            case "l" :
                ctLegend.addNewLegendPos().setVal(STLegendPos.L);
                break;
            case "r" :
                ctLegend.addNewLegendPos().setVal(STLegendPos.R);
                break;
            case "t" :
                ctLegend.addNewLegendPos().setVal(STLegendPos.T);
                break;
            default:
                ctLegend.addNewLegendPos().setVal(STLegendPos.T);
                break;
        }

        return ctPlotArea;
    }

    /**
     * get column index by column address
     */
    public static int getColumnIndexByAddress(String columnAddress) {
        int colNum = 0;

        for (int i = 0; i < columnAddress.length(); i++) {
            char ch = columnAddress.charAt(columnAddress.length() - 1 - i);
            colNum += (ch - 'A' + 1) * Math.pow(26, i);
        }

        return colNum - 1;
    }
}
