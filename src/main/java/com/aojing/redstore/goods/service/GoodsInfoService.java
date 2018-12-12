package com.aojing.redstore.goods.service;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.GoodsDto;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 有选择性的查询
     * @param goodsInfo
     * @return
     */
    Result<List<GoodsInfo>> queryBySelective(GoodsInfo goodsInfo);
    /**
     * 新增或修改商品
     * @param goodsDto
     * @return
     */
    public Result updateGoods(GoodsDto goodsDto);

    public Result addGoods(GoodsDto goodsDto);

    //    Delete[删除商品]
  //  Result Delete();

    //    UploadPicture[上传商品图片]
  //  Result UploadPicture();

    //    DelPicture[删除商品图片]
  //  Result DelPicture();

    //    delFile[删除商品宣传短视频]
  //  Result delFile();

    //    ChangeStatus[修改商品状态]
  //  Result ChangeStatus();

    //    giveLike[点赞]
   // Result giveLike();

    //    comment[评论商品]
  //  Result comment();

    //    discomment[退回评论]
  //  Result discomment();

    //    Examine[审核商品信息]
  //  Result Examine();


}
