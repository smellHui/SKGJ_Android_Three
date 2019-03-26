package com.tepia.base.view.arcgisLayout;

import com.esri.arcgisruntime.arcgisservices.LevelOfDetail;
import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.layers.WebTiledLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleMapLayer {
    private static int minLevel = 0;

    private static int maxLevel = 19;

    private static int dpi = 96;

    private static int titeHeight = 256;

    private static int titeWidth = 256;

    private static double minX = -22041257.773878, minY = -32673939.6727517, maxX = 22041257.773878, maxY = 20851350.0432886, centX = -20037508.342787, centY = 20037508.342787;

    private static int wkid = 3857;

    private static List<LevelOfDetail> mLevelOfDetails;

    private static Point mPoint;

    private static Envelope mEnvelope;

    private static TileInfo mTileInfo;

    private static String[] subDomains = new String[]{"mt1", "mt2", "mt3"};

    private static String URL_VECTOR = "http://{subDomain}.google.cn/maps/vt?lyrs=m@748&gl=cn&x={col}&y={row}&z={level}";

    private static String URL_IMAGE = "http://{subDomain}.google.cn/maps/vt?lyrs=s@112&hl=zh-CN&gl=cn&x={col}&y={row}&z={level}";

    private static String URL_IMAGE_ANNOTATION = "http://{subDomain}.google.cn/maps/vt?lyrs=h@177000000&hl=zh-CN&gl=cn&x={col}&y={row}&z={level}";


    private static String URL_TERRIEN = "http://{subDomain}.google.cn/maps/vt?lyrs=t@748&gl=cn&x={col}&y={row}&z={level}";

    public static double[] scales = new double[]{591657527.591555,

            295828763.79577702, 147914381.89788899, 73957190.948944002,

            36978595.474472001, 18489297.737236001, 9244648.8686180003,

            4622324.4343090001, 2311162.217155, 1155581.108577, 577790.554289,

            288895.277144, 144447.638572, 72223.819286, 36111.909643,

            18055.954822, 9027.9774109999998, 4513.9887049999998, 2256.994353,

            1128.4971760000001};

    private static double[] resolutions = new double[]{156543.03392800014,

            78271.516963999937, 39135.758482000092, 19567.879240999919,

            9783.9396204999593, 4891.9698102499797, 2445.9849051249898,

            1222.9924525624949, 611.49622628138, 305.748113140558,

            152.874056570411, 76.4370282850732, 38.2185141425366,

            19.1092570712683, 9.55462853563415, 4.7773142679493699,

            2.3886571339746849, 1.1943285668550503, 0.59716428355981721,

            0.29858214164761665};



    public static void getLeavel() {

        mLevelOfDetails = new ArrayList<>();

        for (int a = minLevel; a <= maxLevel; a++) {

            LevelOfDetail levelOfDetail = new LevelOfDetail(a, resolutions[a], scales[a]);

            mLevelOfDetails.add(levelOfDetail);

        }

    }

    public static WebTiledLayer createWebTiteLayer(Type type) {

        WebTiledLayer webTiledLayer = null;

        getLeavel();

        mEnvelope = new Envelope(minX, minY, maxX, maxY, SpatialReference.create(wkid));

        mPoint = new Point(centX, centY, SpatialReference.create(wkid));

        mTileInfo = new TileInfo(dpi,

                TileInfo.ImageFormat.PNG24,

                mLevelOfDetails,

                mPoint,

                SpatialReference.create(wkid),

                titeHeight,

                titeWidth

        );

        switch (type) {

            case IMAGE:

                webTiledLayer = new WebTiledLayer(URL_IMAGE, Arrays.asList(subDomains), mTileInfo, mEnvelope);

                webTiledLayer.loadAsync();

                break;

            case VECTOR:

                webTiledLayer = new WebTiledLayer(URL_VECTOR, Arrays.asList(subDomains), mTileInfo, mEnvelope);

                webTiledLayer.loadAsync();

                break;

            case IMAGE_ANNOTATION:

                webTiledLayer = new WebTiledLayer(URL_IMAGE_ANNOTATION, Arrays.asList(subDomains), mTileInfo, mEnvelope);

                webTiledLayer.loadAsync();

                break;

            case URL_TERRIEN:

                webTiledLayer = new WebTiledLayer(URL_TERRIEN, Arrays.asList(subDomains), mTileInfo, mEnvelope);

                webTiledLayer.loadAsync();

                break;

        }

        return webTiledLayer;

    }

    public enum Type {

        VECTOR, IMAGE, IMAGE_ANNOTATION, URL_TERRIEN;

    }

}
