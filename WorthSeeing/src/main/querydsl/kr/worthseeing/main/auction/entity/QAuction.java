package kr.worthseeing.main.auction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuction is a Querydsl query type for Auction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuction extends EntityPathBase<Auction> {

    private static final long serialVersionUID = -1129496896L;

    public static final QAuction auction = new QAuction("auction");

    public final NumberPath<Integer> auction_seq = createNumber("auction_seq", Integer.class);

    public final NumberPath<Integer> auctionPrice = createNumber("auctionPrice", Integer.class);

    public final NumberPath<Integer> finishPrice = createNumber("finishPrice", Integer.class);

    public final NumberPath<Integer> reservation_seq = createNumber("reservation_seq", Integer.class);

    public final DateTimePath<java.util.Date> suggestDate = createDateTime("suggestDate", java.util.Date.class);

    public final NumberPath<Integer> suggestPrice = createNumber("suggestPrice", Integer.class);

    public final StringPath userId = createString("userId");

    public QAuction(String variable) {
        super(Auction.class, forVariable(variable));
    }

    public QAuction(Path<? extends Auction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuction(PathMetadata metadata) {
        super(Auction.class, metadata);
    }

}

