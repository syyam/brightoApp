package com.example.syyam.jobsapp;

import com.example.syyam.jobsapp.Models.CategorySpecific;
import com.example.syyam.jobsapp.Models.ColorFinder;
import com.example.syyam.jobsapp.Models.Countries;
import com.example.syyam.jobsapp.Models.DealerCity;
import com.example.syyam.jobsapp.Models.DesignerPalettesModel.DesignerPalettes;
import com.example.syyam.jobsapp.Models.FinishSpecific;
import com.example.syyam.jobsapp.Models.FurnishFinish;
import com.example.syyam.jobsapp.Models.Like;
import com.example.syyam.jobsapp.Models.LikedProducts;
import com.example.syyam.jobsapp.Models.LikedShades;
import com.example.syyam.jobsapp.Models.Login;
import com.example.syyam.jobsapp.Models.ProductDetailModel.ProductDetailCity;
import com.example.syyam.jobsapp.Models.ProductDetailModel.ProductDetailDatum;
import com.example.syyam.jobsapp.Models.ProductType;
import com.example.syyam.jobsapp.Models.Products;
import com.example.syyam.jobsapp.Models.Register;
import com.example.syyam.jobsapp.Models.ShadesFamily;
import com.example.syyam.jobsapp.Models.ShadesProduct.ShadesProduct;
import com.example.syyam.jobsapp.Models.SpecificShadeModel.SpecificShadeModel;
import com.example.syyam.jobsapp.Models.SurfaceSpecific;
import com.example.syyam.jobsapp.Models.params.CategoryParam;
import com.example.syyam.jobsapp.Models.params.CountryParam;
import com.example.syyam.jobsapp.Models.params.CityParam;
import com.example.syyam.jobsapp.Models.params.IdParam;
import com.example.syyam.jobsapp.Models.params.LoginParam;
import com.example.syyam.jobsapp.Models.params.PaletteParam;
import com.example.syyam.jobsapp.Models.params.ProductParam;
import com.example.syyam.jobsapp.Models.params.ProjectTypeParam;
import com.example.syyam.jobsapp.Models.params.RegisterParam;
import com.example.syyam.jobsapp.Models.params.CountryFamilyParam;
import com.example.syyam.jobsapp.Models.params.ShadeParam;
import com.example.syyam.jobsapp.Models.params.SurfaceParam;
import com.example.syyam.jobsapp.Models.params.UpdateUserParam;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface API {

    //use this in 'a' wali apis - @Header("Authorization") String token
//    @GET("products")
//    Call<Products> getProducts(@Header("Authorization") String token);


    //1
    @GET("family")
    Call<ColorFinder> getColorFinder();

    //2
    @POST("shades/family")
    Call<ShadesFamily> getColorShades(@Body CountryFamilyParam countryFamilyParam);

    //3
    @POST("shade/id")
    Call<SpecificShadeModel> getSpecificShade(@Body IdParam idParam);

    //4
    @POST("shades/product")
    Call<ShadesProduct> getShadesProduct(@Body ProductParam productParam);

    //
    @GET("shades")
    Call<ShadesProduct> getAllShades();


    //6
    @POST("favourite/shade/like")
    Call<Like> getLikeShade(@Header("Authorization") String token, @Body ShadeParam shadeParam);

    //7
    @POST("favourite/shade/unlike")
    Call<Like> getUnLikeShade(@Header("Authorization") String token, @Body ShadeParam shadeParam);

    //8
    @GET("favourite/shades")
    Call<LikedShades> getLikedShades(@Header("Authorization") String token);

    //9
    @GET("project-type")
    Call<ProductType> getProductType();

    //10
    @POST("category/specific")
    Call<CategorySpecific> getCategorySpecific(@Body ProjectTypeParam projectTypeParam);

    //11
    @POST("surface/specific")
    Call<SurfaceSpecific> getSurfaceSpecific(@Body CategoryParam categoryParam);

    //12
    @POST("finish-type/specific")
    Call<FinishSpecific> getFinishSpecific(@Body SurfaceParam surfaceParam);

    //13
    @POST("products/filter")
    Call<Products> getProductsFilter(@Body FilterParam filterParam);

    //21
    @GET("country")
    Call<Countries> getCountries();

    //22
    @POST("city/specific")
    Call<Countries> getCities(@Body CountryParam countryParam);

    //23
//    @POST("dealer/country")
//    Call<Countries> getDealerCountry(@Body CountryParam countryParam);

    //24
    @POST("dealer/city")
    Call<DealerCity> getDealerCities(@Body CityParam cityParam);

    //24

    @POST("forgot-password")
    Call<ResponseBody> forgotPassword(@Query("email") String email);

    //25
//    @POST("dealer/specific")
//    Call<Countries> getDealerSpecific(@Body DealerParam dealerParam);

    //16
    @POST("product/id")
    Call<ProductDetailCity> getProductDetail(@Body ProductParam productParam);

    //17
    @POST("product/country")
    Call<Products> getProducts(@Body CountryParam countryParam);

    //18
    @POST("favourite/products/like")
    Call<Like> getLike(@Header("Authorization") String token, @Body ProductParam productParam);

    //19
    @POST("favourite/products/unlike")
    Call<Like> getUnLike(@Header("Authorization") String token, @Body ProductParam productParam);

    //20
    @GET("favourite/products")
    Call<LikedProducts> getLikedProducts(@Header("Authorization") String token);
//35
    @GET("favourite/pallets")
    Call<LikedProducts> getLikePallets(@Header("Authorization") String token);

    //32
    @GET("luxury-finish")
    Call<FurnishFinish> getAllLuxuryFinish();
    //27
    @POST("login")
    Call<Login> getLogin(@Body LoginParam loginParam);




    //26
    @POST("signup")
    Call<Register> getRegister(@Body RegisterParam registerParam);

    //31
    @GET("pallet")
    Call<DesignerPalettes> getDesignerPalettes();

    //33
    @POST("favourite/pallets/like")
    Call<Like> getLikePalettes(@Header("Authorization") String token, @Body PaletteParam paletteParam);

    //34
    @POST("favourite/pallets/unlike")
    Call<Like> getUnLikePalettes(@Header("Authorization") String token, @Body PaletteParam paletteParam);

    //users
    @PUT("user/id")
    Call<Like> putUpdateUser(@Body UpdateUserParam updateUserParam);


    @GET("pallet")
    Call<DesignerPalettes> getAllPallettes();

}
