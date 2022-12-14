package kr.worthseeing.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = 1409820795L;

    public static final QUsers users = new QUsers("users");

    public final StringPath adminyn = createString("adminyn");

    public final StringPath adress = createString("adress");

    public final StringPath blackyn = createString("blackyn");

    public final StringPath detatiladress = createString("detatiladress");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> finishedAuctionCnt = createNumber("finishedAuctionCnt", Integer.class);

    public final StringPath joindate = createString("joindate");

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Integer> reservationCnt = createNumber("reservationCnt", Integer.class);

    public final StringPath tel = createString("tel");

    public final NumberPath<Integer> totalMoney = createNumber("totalMoney", Integer.class);

    public final StringPath userId = createString("userId");

    public final StringPath userPw = createString("userPw");

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

