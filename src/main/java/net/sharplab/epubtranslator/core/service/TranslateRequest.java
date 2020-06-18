package net.sharplab.epubtranslator.core.service;

import net.sharplab.epubtranslator.core.util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSSerializer;

import java.util.List;

/**
 * 翻訳リクエスト
 *
 * 対象のドキュメントにおける翻訳要求の一つを表現するデータ構造
 */
public class TranslateRequest {

    /**
     * 翻訳対象ドキュメント
     */
    private Document document;

    /**
     * 翻訳対象ノードのリスト
     */
    private List<Node> target;


    /**
     * 翻訳リクエストのコンストラクタ
     * @param document 翻訳対象ドキュメント
     * @param target 翻訳対象ノードのリスト
     */
    public TranslateRequest(Document document, List<Node> target){
        this.document = document;
        this.target = target;
    }

    public Document getDocument() {
        return document;
    }

    public List<Node> getTarget() {
        return target;
    }

    /**
     * 翻訳対象ノードの文字列表現を返却する
     */
    public String getSourceXmlString(){
        StringBuilder stringBuilder = new StringBuilder();
        LSSerializer lsSerializer = XmlUtils.getLsSerializer(document);
        for (Node node : target) {
            stringBuilder.append(lsSerializer.writeToString(node));
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString(){
        return getSourceXmlString();
    }
}
