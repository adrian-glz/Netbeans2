package reportes.util;

import java.io.StringReader;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Content;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;


/**
 *
 * @author jmendoza
 */

public class ResultXML {
    
    static Document doc;
    static private String GetCompetitivePricingForSKUResult_status="";
    static private String GetCompetitivePricingForSKUResult_SellerSKU="";
    static private String Product_ASIN="";
    static private Boolean CompetitivePrice_belongsToRequester=false;
    static private String LandedPrice_CurrencyCode="";
    static private Double LandedPrice_Amount=0.0;
    static private int OfferListingCount_condition_Any=0;
    static private String SalesRank_ProductCategoryId_ce_display_on_website="";
    static private int SalesRank_ProductCategoryId_Rank=0;
    static private String SalesRank_ProductCategoryId_nodo="";
    static private int SalesRank_ProductCategoryId_nodo_Rank=0;
    
    
    public static void setValuesXML(String cXML) {
        SAXBuilder sxb = new SAXBuilder();
        GetCompetitivePricingForSKUResult_status="";
        GetCompetitivePricingForSKUResult_SellerSKU="";
        Product_ASIN="";        
        CompetitivePrice_belongsToRequester=false;
        LandedPrice_CurrencyCode="";
        LandedPrice_Amount=0.0;
        OfferListingCount_condition_Any=0;
        SalesRank_ProductCategoryId_ce_display_on_website="";
        SalesRank_ProductCategoryId_Rank=999999;
        SalesRank_ProductCategoryId_nodo="";
        SalesRank_ProductCategoryId_nodo_Rank=999999;
        try {
            doc = sxb.build(new InputSource(new StringReader(cXML)));
            
            Element root = doc.getRootElement();
            Namespace ns = root.getNamespace();
            System.out.println(root.getNamespacesIntroduced());
            
            Element GetCompetitivePricingForSKUResult = root.getChild("GetCompetitivePricingForSKUResult", ns); 
            
            Attribute status = GetCompetitivePricingForSKUResult.getAttribute("status");
            if (status != null) {
                GetCompetitivePricingForSKUResult_status = status.getValue();
                System.out.println(GetCompetitivePricingForSKUResult_status);
            }           

            Attribute SellerSKU = GetCompetitivePricingForSKUResult.getAttribute("SellerSKU");
            if (SellerSKU != null) {
                GetCompetitivePricingForSKUResult_SellerSKU = SellerSKU.getValue();
                System.out.println(GetCompetitivePricingForSKUResult_SellerSKU);
            }
            
            Element Product = GetCompetitivePricingForSKUResult.getChild("Product", ns);
            Element Identifiers = Product.getChild("Identifiers", ns);
            Element MarketplaceASIN = Identifiers.getChild("MarketplaceASIN", ns);
            Element ASIN = MarketplaceASIN.getChild("ASIN", ns);
            if (ASIN != null) {
                Product_ASIN = ASIN.getValue();
                System.out.println(Product_ASIN);
            }
            
            Element CompetitivePricing = Product.getChild("CompetitivePricing", ns);
            Element CompetitivePrices = CompetitivePricing.getChild("CompetitivePrices", ns);
            Element CompetitivePrice = CompetitivePrices.getChild("CompetitivePrice", ns);
            Attribute belongsToRequester = CompetitivePrice.getAttribute("belongsToRequester");
            if (belongsToRequester != null) {
                CompetitivePrice_belongsToRequester = ("true".equals(belongsToRequester.getValue()));
                System.out.println(CompetitivePrice_belongsToRequester);
            }
             
            Element Price = CompetitivePrice.getChild("Price", ns);
            Element LandedPrice = Price.getChild("LandedPrice", ns);
            Element CurrencyCode = LandedPrice.getChild("CurrencyCode", ns);
            if (CurrencyCode != null) {
                LandedPrice_CurrencyCode = CurrencyCode.getValue();
                System.out.println(LandedPrice_CurrencyCode);
            }
            
            Element Amount = LandedPrice.getChild("Amount", ns);
            if (Amount != null) {
                LandedPrice_Amount = Double.parseDouble(Amount.getValue().trim());
                System.out.println(LandedPrice_Amount);
            }
            
            Element NumberOfOfferListings = CompetitivePricing.getChild("NumberOfOfferListings", ns);
            List<Element> OfferListingCounts = NumberOfOfferListings.getChildren("OfferListingCount", ns); 
            for (int i = 0; i < OfferListingCounts.size(); i++) {
                if ("Any".equals(OfferListingCounts.get(i).getAttribute("condition").getValue())) {
                    OfferListingCount_condition_Any = Integer.parseInt(OfferListingCounts.get(i).getValue());
                    System.out.println(OfferListingCount_condition_Any);
                    break;
                }
            }

            Element SalesRankings = Product.getChild("SalesRankings", ns);
            List<Element> SalesRanks = SalesRankings.getChildren("SalesRank", ns); 
            for (int i = 0; i < SalesRanks.size(); i++) {
                if ("ce_display_on_website".equals(SalesRanks.get(i).getChild("ProductCategoryId", ns).getValue())) {
                    SalesRank_ProductCategoryId_ce_display_on_website = SalesRanks.get(i).getChild("ProductCategoryId", ns).getValue();
                    System.out.println(SalesRank_ProductCategoryId_ce_display_on_website);
                    SalesRank_ProductCategoryId_Rank = Integer.parseInt(SalesRanks.get(i).getChild("Rank", ns).getValue());
                    System.out.println(SalesRank_ProductCategoryId_Rank);
                } else {
                    SalesRank_ProductCategoryId_nodo = SalesRanks.get(i).getChild("ProductCategoryId", ns).getValue();
                    System.out.println(SalesRank_ProductCategoryId_nodo);
                    SalesRank_ProductCategoryId_nodo_Rank = Integer.parseInt(SalesRanks.get(i).getChild("Rank", ns).getValue());
                    System.out.println(SalesRank_ProductCategoryId_nodo_Rank);                    
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
 
    
    public static String getstatus() {
        return Util.cFixString(GetCompetitivePricingForSKUResult_status);        
    }

    public static String getSellerSKU() {
        return Util.cFixString(GetCompetitivePricingForSKUResult_SellerSKU);        
    }
    
    public static String getASIN() {
        return Util.cFixString(Product_ASIN);        
    }

    public static Boolean getbelongsToRequester() {
        return CompetitivePrice_belongsToRequester;        
    }

    public static String getLandedPrice_CurrencyCode() {
        return Util.cFixString(LandedPrice_CurrencyCode);        
    }

    public static Double getLandedPrice_Amount() {
        return LandedPrice_Amount;        
    }

    public static int getOfferListingCount_condition_Any() {
        return OfferListingCount_condition_Any;        
    }

    public static String getSalesRank_ProductCategoryId_ce_display_on_website() {
        return Util.cFixString(SalesRank_ProductCategoryId_ce_display_on_website);        
    }

    public static int getSalesRank_ProductCategoryId_Rank() {
        return SalesRank_ProductCategoryId_Rank;        
    }

    public static String getSalesRank_ProductCategoryId_nodo() {
        return Util.cFixString(SalesRank_ProductCategoryId_nodo);        
    }

    public static int getSalesRank_ProductCategoryId_nodo_Rank() {
        return SalesRank_ProductCategoryId_nodo_Rank;        
    }
    
}
