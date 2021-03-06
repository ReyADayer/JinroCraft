[![CircleCI](https://circleci.com/gh/ReyADayer/JinroCraft.svg?style=svg)](https://circleci.com/gh/ReyADayer/JinroCraft)

# 人狼クラフト

サバイバルワールドで人狼ゲームができるプラグインです。

# 遊び方

## ゲームの設定

 `/role setting <役職英語名> <人数>`
で役職内訳を設定することできます。
市民は設定する必要はありません。

例

 `/role setting Werewolf 3`
|  役職  |  英語名  |
| ---- | ---- |
|  市民  |  Citizen  |
|  占い師  |  Seer  |
|  霊能者  |  Medium  |
|  狩人  |  Hunter  |
|  人狼  |  Werewolf  |
|  狂人  |  Madman  |
|  妖狐  |  Fox  |

詳細はRoleType.ktを参照してください

## ゲームの開始

オペレーターが `/game start` を実行すると役職が自動に割り振られゲームがスタートします。

`/status` コマンドで自分の役職を確認できます。

人狼は `/jc <会話の内容>` で人狼同士で会話することができます。

![2020-08-20_14 01 42](https://user-images.githubusercontent.com/17574089/90720107-e6c63800-e2f0-11ea-9ea1-eb8e70f7f821.png)

一度死ぬとプレイヤーの墓が生成され、スペクターモードになります。

![2020-08-20_14 01 57](https://user-images.githubusercontent.com/17574089/90720095-df9f2a00-e2f0-11ea-9cd1-f95165f9e605.png)

`/co` でカミングアウトすることができます。

![2020-08-20_14 27 53](https://user-images.githubusercontent.com/17574089/90720385-65bb7080-e2f1-11ea-8323-f68f87d6c81a.png)

## 役職の能力

|  役職  |  能力  |
| ---- | ---- |
|市民 |能力はありません |
|霊能者 |ハサミを手に持って墓を右クリックすると、死亡したプレイヤーが人狼であるかどうかを判定できます |
|占い師 |ハサミを手に持ってプレイヤーを右クリックすると経験値を10消費して人狼であるかどうかを判定できます |
|狩人 |一定時間ごとに矢を入手します |
|人狼 |夜の間、攻撃によるダメージが2増加します |
|狂人 |能力はありません |
|妖狐 |人狼からダメージを受けません。占い師に占われると死亡します。 |
 

## 一日の流れ

夕方になると投票タイムとなります。
`/vote <プレイヤー名>` で処刑するプレイヤーに投票します。
一番票が多いプレイヤーが処刑されます。
 
 ## ゲームの終了
 
 いずれかの陣営が勝利条件を満たすとゲーム終了です。
 
 

![2020-08-20_14 02 26](https://user-images.githubusercontent.com/17574089/90720121-ecbc1900-e2f0-11ea-8743-aeb2f2aaad6b.png)

 

| 陣営   | 条件                  |
|--------|-----------------------|
| 村人陣営 | 人狼、妖狐をすべて倒す       |
| 人狼陣営 | 人狼の数が村人の数を上回ったとき |
| 妖狐陣営 | 妖狐が最後まで生き残る       |

# 開発

## docker-composeのインストール

### MacOSでの手順

Ruby2.6.3をインストールしてください

```
brew install rbenv
rbenv install 2.6.3
```

docker-composeをインストール
```
gem install docker-compose
```

## コンテナの起動

コンテナを起動してください。

```
docker-compose build
docker-compose up
```

コンテナ起動後、Minecraftでlocalhostに接続してください