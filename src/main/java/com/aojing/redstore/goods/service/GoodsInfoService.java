package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品信息
 *
 * @author gexiao
 * @date 2018/12/4 17:20
 */
public interface GoodsInfoService {
    //    Disrecomend[解除推荐]
  // Result Disrecomend();

    //    Recomend[推荐商品]
  //  Result Recomend();

    //    UploadMovie[上传视频]
  //  Result UploadMovie();
    Result UploadMovie(MultipartFile file, String path);

    //    Query[根据商品类型查询商品信息]
  //  Result Query();

    //    Add[添加商品（新建商品）]
  //  Result Add();

    //    Updata[修改商品信息]
  //  Result Updata();

    //    Delete[删除商品]
  //  Result Delete();

    //    UploadPicture[上传商品图片]
  //  Result UploadPicture();

    //    DelPicture[删除商品图片]
  //  Result DelPicture();
    public Result delMovie(String fileName, String mediaInfoId);

    //    delMovie[删除商品宣传短视频]
  //  Result delMovie();

    //    ChangeStatus[修改商品状态]
  //  Result ChangeStatus();

    //    GiveLike[点赞]
   // Result GiveLike();

    //    Comment[评论商品]
  //  Result Comment();

    //    Discomment[退回评论]
  //  Result Discomment();

    //    Examine[审核商品信息]
  //  Result Examine();


}
