package openfoodfacts.rackluxury.scrachx.openfood.models.entities.brand;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

@Entity(indexes = {
    @Index(value = "languageCode, brandTag", unique = true)
})
public class BrandName {

    @Id(autoincrement = true)
    Long id;

    private String brandTag;

    private String languageCode;

    private String name;

    private String wikiDataId;

    private Boolean isWikiDataIdPresent;

    @Keep
    public BrandName(String brandTag, String languageCode, String name, String wikiDataId) {
        this.brandTag = brandTag;
        this.languageCode = languageCode;
        this.name = name;
        this.wikiDataId = wikiDataId;
        this.isWikiDataIdPresent = true;
    }

    @Keep
    public BrandName(String brandTag, String languageCode, String name) {
        this.brandTag = brandTag;
        this.languageCode = languageCode;
        this.name = name;
    }

    @Keep
    public BrandName(String name) {
        this.name = name;
    }

    @Generated(hash = 1584626266)
    public BrandName(Long id, String brandTag, String languageCode, String name,
            String wikiDataId, Boolean isWikiDataIdPresent) {
        this.id = id;
        this.brandTag = brandTag;
        this.languageCode = languageCode;
        this.name = name;
        this.wikiDataId = wikiDataId;
        this.isWikiDataIdPresent = isWikiDataIdPresent;
    }

    @Generated(hash = 2023712254)
    public BrandName() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandTag() {
        return brandTag;
    }

    public void setBrandTag(String brandTag) {
        this.brandTag = brandTag;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiDataId() {
        if (this.wikiDataId == null) {
            return "null";
        }
        String res = this.wikiDataId;
        int startIndex = res.indexOf("en");
        startIndex = startIndex + 5;
        int lastIndex = res.lastIndexOf("\"");
        if (startIndex < 3 || lastIndex < 3) {
            return res;
        }
        res = res.substring(startIndex, lastIndex);
        return res;
    }

    public void setWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }

    public Boolean getWikiDataIdPresent() {
        return isWikiDataIdPresent;
    }

    public void setWikiDataIdPresent(Boolean wikiDataIdPresent) {
        isWikiDataIdPresent = wikiDataIdPresent;
    }

    public Boolean isNull() {
        return id == null && brandTag == null && languageCode == null && name == null;
    }

    public Boolean isNotNull() {
        return id != null && brandTag != null && languageCode != null && name != null;
    }

    public Boolean getIsWikiDataIdPresent() {
        return this.isWikiDataIdPresent;
    }

    public void setIsWikiDataIdPresent(Boolean isWikiDataIdPresent) {
        this.isWikiDataIdPresent = isWikiDataIdPresent;
    }
}

