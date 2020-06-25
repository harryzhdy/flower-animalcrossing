package com.springboot.mybatis.services;

import com.springboot.mybatis.entity.Flower;
import com.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowerService {

    private boolean isRose = false;
    private int allCount = 64;
    private String f1;
    private String f2;

    public FlowerService(String f1, String f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public void init() {
        if (f1.length() == 8) {
            isRose = true;
            allCount = 256;
        }
    }

   /* public static void main(String[] args) {

        FlowerService flowerService = new FlowerService("rryywwss", "RrYyWWss");
        flowerService.init();
        //Map<String, Double> resultMap = flowerService.getResult();

    }*/

    public Map<String, Double> getResult() {
        HashMap<String, Double> resultMap = new HashMap<>();
        List<String> g1 = getGeneResult(f1.substring(0, 2), f2.substring(0, 2));
        List<String> g2 = getGeneResult(f1.substring(2, 4), f2.substring(2, 4));
        List<String> g3 = getGeneResult(f1.substring(4, 6), f2.substring(4, 6));
        List<String> g4 = new ArrayList<>();
        if (isRose) {
            g4 = getGeneResult(f1.substring(6, 8), f2.substring(6, 8));
        }
        for (String gp1 : g1) {
            for (String gp2 : g2) {
                for (String gp3 : g3) {
                    if (!isRose) {
                        String gene = gp1 + gp2 + gp3;
                        if (!resultMap.containsKey(gene)) {
                            resultMap.put(gene, 1.0);
                        } else {
                            resultMap.put(gene, resultMap.get(gene) + 1);
                        }
                    } else {
                        for (String gp4 : g4) {
                            String gene = gp1 + gp2 + gp3 + gp4;
                            if (!resultMap.containsKey(gene)) {
                                resultMap.put(gene, 1.0);
                            } else {
                                resultMap.put(gene, resultMap.get(gene) + 1);
                            }
                        }
                    }
                }
            }
        }
        for (String gene : resultMap.keySet()) {
            resultMap.put(gene, resultMap.get(gene)/allCount);
        }


        return resultMap;
    }

    private static List<String> getGeneResult(String g1, String g2) {
        List<String> geneList = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                StringBuilder sb = new StringBuilder();
                char ch1 = g1.charAt(i);
                char ch2 = g2.charAt(j);
                if (ch1 < ch2) {
                    sb.append(ch1).append(ch2);
                } else {
                    sb.append(ch2).append(ch1);
                }
                geneList.add(sb.toString());
            }
        }
        return geneList;
    }
}
