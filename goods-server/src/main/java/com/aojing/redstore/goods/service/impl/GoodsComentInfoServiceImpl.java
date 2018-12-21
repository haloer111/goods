package com.aojing.redstore.goods.service.impl;

import java.util.ArrayList;
import java.util.Date;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsComentInfoMapper;
import com.aojing.redstore.goods.dao.GoodsLikeInfoMapper;
import com.aojing.redstore.goods.dto.QueryCountDto;
import com.aojing.redstore.goods.enums.ExceptionEnum;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.aojing.redstore.goods.service.GoodsComentInfoService;
import com.aojing.redstore.goods.util.KeyUtil;
import com.aojing.redstore.goods.vo.CommentVo;
import com.aojing.redstore.media.client.MediaClient;
import com.aojing.redstore.media.common.ImgInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gexiao
 * @date 2018/12/6 10:41
 */
@Service
public class GoodsComentInfoServiceImpl implements GoodsComentInfoService {

    @Autowired
    private GoodsComentInfoMapper goodsComentInfoMapper;

    @Autowired
    private GoodsLikeInfoMapper goodsLikeInfoMapper;

    @Autowired
    private MediaClient mediaClient;

    //    comment[评论商品]
    public Result comment(GoodsComentInfo comentInfo) {
        if (comentInfo == null) {
            return Result.createByErrorMessage("评论商品,参数不正确");
        }
        comentInfo.setId(KeyUtil.getkey());
        int result = goodsComentInfoMapper.insertSelective(comentInfo);
        if (result > 0) {
            return Result.createBySuccessMessage("评论商品,成功");

        }
        return Result.createByErrorMessage("评论商品,失败");

    }


    //    discomment[退回评论]
    public Result discomment(String id, String commenterId) {
        if (id == null) {
            return Result.createByErrorMessage("退回评论,参数不正确");
        }

        int result = goodsComentInfoMapper.deleteByIdAndUserId(id, commenterId);
        if (result > 0) {
            return Result.createBySuccessMessage("评论商品,成功");
        }
        return Result.createByErrorMessage("评论商品,失败");
    }

    public Result<List<Map<String, Object>>> commentCount(QueryCountDto queryCountDto) {
        List<Map<String, Object>> countList=null;
        if (!CollectionUtils.isEmpty(queryCountDto.getGoodsIdList())){
            countList = goodsComentInfoMapper.queryCommentCountByGoodsIdList(queryCountDto.getGoodsIdList());
        }
        if (!CollectionUtils.isEmpty(queryCountDto.getSellerIdList())){
            countList = goodsComentInfoMapper.queryCommentCountBySellerIdList(queryCountDto.getSellerIdList());
        }

        return Result.createBySuccess(countList);

    }

    @Override
    public Result<List<CommentVo>> getAllCommentByGoodsId(String goodsId) {
        List<CommentVo> commentVoList = new ArrayList<>();
        // 1.查询评论表
        List<GoodsComentInfo> commentInfoList = goodsComentInfoMapper.selectByGoodsId(goodsId);
        //用户idList集合
        List<String> userIdList = commentInfoList.stream().map(e -> e.getCommenterId()).collect(Collectors.toList());

        for (GoodsComentInfo comentInfo : commentInfoList) {
            CommentVo commentVo = new CommentVo();
            commentVo.setCommentId(comentInfo.getId());
            commentVo.setUserId(comentInfo.getCommenterId());
            commentVo.setUserName("");
            commentVo.setContent(comentInfo.getContent());
            commentVo.setCommentTime(comentInfo.getCreateTime());

            //组装点赞数
            Integer giveLikeCount = goodsLikeInfoMapper.queryLikeInfoCountByCommentId(comentInfo.getId());
            commentVo.setGiveLikeCount(giveLikeCount);

            commentVoList.add(commentVo);
        }

        // 2.用户id去查询头像[调用商品服务]
        // todo 还差个用户名,[调用用户服务]
        List<ImgInput> imgInputs = mediaClient.queryIconByUserId(userIdList);
        for (CommentVo commentVo : commentVoList) {
            for (ImgInput imgInput : imgInputs) {
                if (commentVo.getUserId().equals(imgInput.getEntityId())) {
                    commentVo.setIcon(imgInput.getIcon());
                }
            }
        }

        return Result.createBySuccess(commentVoList);
    }
}
