# 코디 완성 서비스
## 서비스 개요 :
8가지 카테고리에서 상품을 하나씩 구매하여, 코디를 완성하는 서비스
1. 상의
2. 아우터
3. 스니커즈
4. 가방
5. 모자
6. 양말
7. 액세사리

## 기술스택
* 언어 : Java17(백엔드) , Node(v16)
* 프레임워크 : SpringBoot (v3.2.1), Spring Data JPA, React
* 데이터베이스 : H2

## 구현범위
### 브랜드 (Brand)
1. 브랜드 생성
- 브랜드 키와 판매코드 (SALE/UNSALE), 브랜드 이름, 비고를 입력받아 브랜드를 생성할 수 있다.
- 브랜드 이름과 브랜드 키는 유니크 제약 조건을 설정하여, brandkey, brandName의 조합이 중복되지 않도록 보장한다.
2. 브랜드 수정
- 수정할 brand_id, 판매 코드 sale_state_code, 브랜드 이름 brand_name, 비고 remark를 입력받아 브랜드를 수정할 수 있다.
- 수정할 brand_name은 유니크 제약 조건 상 brand_name, brand_key가 중복되지 않도록 보장. (브랜드 이름은 같을 수 있다.)
3. 브랜드 조회
- 등록된 전체 브랜드를 조회할 수 있다.
- 단일 브랜드를 조회할 수 있다.
4. 브랜드 삭제
- 삭제할 brand_id를 입력받아 삭제할 수 있다.
- 삭제된 brand와 연관관계가 맺어진 모든 상품도 함께 삭제가 된다.
5. 브랜드의 상품 조회
- 최저가 코디에 해당되는 브랜드와 그에 해당하는 상품들을 조회할 수 있다.
- 모든 카테고리를 지닌 브랜드를 대상으로 조회를 할 수 있다.
- 최저가가 겹치는 브랜드는 복수로 조회가 된다.


### 상품 (Product)
1. 상품 등록
- 상품 가격(price), 상품 키(product_key), 브랜드(brand_id), 카테고리(category_type), 비고(remark)를 입력받아 상품을 생성할 수 있다.
- 브랜드 (brand)기준으로 brand와 1:N 연관관계가 설정 되었다.
- 카테고리는 (category_type) 코디 서비스에서 다루는 카테고리만 다룬다.
- 가격(price) 설정 시 음수는 다루지 않는다.
2. 상품 수정
- 카테고리(category_type), 상품 가격(price), 비고(remark), 수정될 상품(product_it)를 입력받아 상품을 수정할 수 있다.
- 가격(price) 설정 시 음수는 다루지 않는다.
- 카테고리는 (category_type) 코디 서비스에서 다루는 카테고리만 다룬다.
3. 상품 삭제
- 삭제될 상품(product_id)를 입력받아 상품을 삭제할 수 있다.
4. 상품 조회
- 브랜드(brand_id)또는 카테고리(category_type)를 입력받아 입력된 조건에 해당되는 상품을 조회할 수 있다.
- 브랜드(brand_id)와 카테고리(category_type)를 입력받아 입력된 조건에 해당되는 상품을 조회할 수 있다.
4. 카테고리 조회
- 모든 카테고리를 조회할 수 있다.
5. 특정 카테고리의 최저가, 최고가 상품 조회
- 카테고리(category_type)를 입력받아 상품의 최저가와 최고가에 해당하는 상품을 조회할 수 있다.
6. 카테고리 별 최저가 상품 조회
- 카테고리별 최저가에 해당하는 상품을 조회할 수 있다.

## 빌드 && 실행 (백엔드)
```
(H2 database 실행 전제)
git clone https://github.com/tkdlqm2/app-category.git
cd app-category
./gradlew clean
./gradlew build
(실행 명령어) java -jar app-category/build/libs/app-category-0.0.1-SNAPSHOT.jar
```

## 빌드 && 실행 (프론트)
```
git clone https://github.com/tkdlqm2/app-category-front.git
cd app-category-front
npm install
(실행 명령어) npm start
```

## 테스트 데이터 생성
```
브랜드 생성   
curl  -X GET http://127.0.0.1:18083/api/v1/insert/brand

상품 생성
curl  -X GET http://127.0.0.1:18083/api/v1/insert/product
```

## 테스트 방법
1. 프론트 앤드
   http://127.0.0.1:3000/
2. swagger
   http://127.0.0.1:18083/swagger-ui/index.html#/product%20API/getCategoryList