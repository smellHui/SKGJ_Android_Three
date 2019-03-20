package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.setting.DutyOfWorkBean;
import com.tepia.main.model.worknotification.WorkNoticeListResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Joeshould on 2018/5/22.
 */

interface UserHttpService {
    @FormUrlEncoded
    @POST("jwt/app/getToken")
    Observable<UserLoginResponse> login(@Field("logincode") String name,
                                        @Field("password") String password,
                                        @Field("registId") String registId);

    /**
     * 修改密码
     *
     * @param newPassword
     * @param password
     * @return
     */
    @PUT("app/appSysUser/updatePwd")
    Observable<BaseResponse> updatePwd(@Header("Authorization") String token,
                                       @Query("newPassword") String newPassword,
                                       @Query("password") String password);

    /**
     * 获取用户登录信息
     *
     * @param token
     * @return
     */
    @GET("app/appSysUser/getLoginUser")
    Observable<UserInfoBean> getLoginUser(@Header("Authorization") String token
    );

    /**
     * 获取动态菜单
     *
     * @param token
     * @return
     */
    @GET("app/sysMenu/getByToken")
    Observable<MenuBean> getByTokenMenu(@Header("Authorization") String token);

    /**
     * 获取动态菜单 2
     *
     * @param token
     * @return
     */
    @GET("app/sysMenu/getByToken")
    Observable<MenuListResponse> getByTokenMenu2(@Header("Authorization") String token);

    /**
     * 编辑用户信息
     *
     * @param token
     * @param userName
     * @param email
     * @param mobile
     * @param address
     * @return
     */
    @PUT("app/appSysUser/updateSysUser")
    Observable<BaseResponse> updateSysUser(@Header("Authorization") String token,
                                           @Query("userName") String userName,
                                           @Query("email") String email,
                                           @Query("mobile") String mobile,
                                           @Query("address") String address
    );


    @GET("app/appSysUser/getUserJob")
    Observable<DutyOfWorkBean> getUserJob(@Header("Authorization") String token);


    /**
     * @param token
     * @return
     */
    @GET("app/reservoirBase/getReservoirList")
    Observable<ReservoirListResponse> getReservoirList(@Header("Authorization") String token);

    /**
     * @param token
     * @param reservoirId
     * @param types
     * @return
     */
    @GET("app/reservoirBase/appHomeGetReservoirInfo")
    Observable<HomeGetReservoirInfoResponse> getAppHomeGetReservoirInfo(@Header("Authorization") String token,
                                                                        @Query("reservoirId") String reservoirId,
                                                                        @Query("types") String types);

    /**
     * 查询通讯录
     * @param token
     * @param searchKey
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appSysUser/getAddressBook")
    Observable<AddressBookResponse> getAddressBook(@Header("Authorization") String token,
                                                   @Query("searchParam") String searchKey,
                                                   @Query("currentPage")String currentPage,
                                                   @Query("pageSize") String pageSize);


    /**
     * 通讯录新增
     * @param token
     * @param searchKey
     *
     * @return
     */
    @GET("app/appSysUser/listReservoirAddressBook")
    Observable<ReserviorBookBean> listReservoirAddressBook(@Header("Authorization") String token,
                                                   @Query("searchParam") String searchKey
                                                  );

    /**
     * 三防办
     * @param token
     * @param searchKey
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appSysUser/listThreeAddressBook")
    Observable<AddressBookResponse> listThreeAddressBook(@Header("Authorization") String token,
                                                           @Query("searchParam") String searchKey,
                                                           @Query("currentPage")String currentPage,
                                                           @Query("pageSize") String pageSize);

}
