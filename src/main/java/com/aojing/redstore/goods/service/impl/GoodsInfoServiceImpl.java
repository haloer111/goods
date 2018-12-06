package com.aojing.redstore.goods.service.impl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dao.GoodsInfoMapper;
import com.aojing.redstore.goods.exception.RedStoreException;
import com.aojing.redstore.goods.pojo.GoodsInfo;
import com.aojing.redstore.goods.service.GoodsInfoService;
import com.aojing.redstore.goods.util.KeyUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author gexiao
 * @date 2018/12/5 15:10
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    RestTemplate restTemplate;

    //    UploadMovie[上传视频] UploadPicture[上传商品图片]
    Result UploadMovie(MultipartFile file, String path) {
        if (file == null || path == null) {
            return Result.createByErrorMessage("商品上传,参数错误");
        }
        Map map = new HashMap();
        map.put("file", file);
        map.put("path", path);
        try {
            Object o = restTemplate.postForObject("http://media/upload", map, Object.class);
        } catch (RestClientException e) {
            //做处理
            throw new RedStoreException(-1, "未找到数据");
        }
        return Result.createBySuccess();
    }

    //    DelMovie[删除商品宣传短视频]  DelPicture[删除商品图片]
    Result DelMovie(String fileName, String mediaInfoId) {
        if (StringUtils.isNotBlank(fileName) && StringUtils.isNotBlank(mediaInfoId)) {
            return Result.createByErrorMessage("商品删除文件,参数错误");
        }
        try {
            //根据主键查询附件表.得到文件名
            Object o = restTemplate.postForObject("http://media/query", mediaInfoId, Object.class);
            //根据文件名来删除
            Object o1 = restTemplate.postForObject("http://media/upload", fileName, Object.class);

        } catch (RestClientException e) {
            //做处理
            throw new RedStoreException(-1, "未找到数据");
        }
        return Result.createBySuccess();
    }


    //    Query[根据商品类型查询商品信息]
    Result Query(GoodsInfo goodsInfo) {
        if (goodsInfo == null) {
            return Result.createByErrorMessage("查询商品,参数不正确");
        }
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.queryByGoodsInfo(goodsInfo);
        if (!goodsInfoList.isEmpty()) {
            return Result.createBySuccess("查询商品,成功", goodsInfoList);

        }
        return Result.createByErrorMessage("查询商品,失败");

    }

    //    Add[添加商品（新建商品）] or Updata[修改商品信息]  ChangeStatus[修改商品状态]
    Result AddOrUpdate(GoodsInfo goodsInfo, MultipartFile file) {
        if (goodsInfo != null) {
            if (goodsInfo.getId() != null) {
                int rowCount = goodsInfoMapper.updateByPrimaryKey(goodsInfo);
                if (rowCount > 0) {
                    return Result.createBySuccess("更新商品成功");
                }
                return Result.createBySuccess("更新商品失败");
            } else {
                // 1. uuid生成goodsid,
                String goodsId = KeyUtil.createKey();
                String mediaId = KeyUtil.createKey();
                //todo 商品id,media_id应该用String来存储
                //goodsInfo.setId(Integer.parse(goodsId));
//                goodsInfo.setMediaId(Integer.parse(goodsId));
                // 2. 判断是否有附件
                // 3. 上传附件,并回传个地址写入附件表
                // 4. 循环插入附件表


                int rowCount = goodsInfoMapper.insert(goodsInfo);
                if (rowCount > 0) {
                    return Result.createBySuccess("新增商品成功");
                }
                return Result.createBySuccess("新增商品失败");
            }
        }
        return Result.createByErrorMessage("新增或更新商品参数不正确");

    }

    //    Delete[删除商品]
    Result Delete(String goodsId) {
        if (goodsId == null) {
            return Result.createByErrorMessage("删除商品,参数不正确");
        }
        int result = goodsInfoMapper.deleteByPrimaryKey(goodsId);
        if (result > 0) {
            return Result.createBySuccessMessage("删除商品,成功");
        }
        return Result.createByErrorMessage("删除商品,失败");
    }


}
