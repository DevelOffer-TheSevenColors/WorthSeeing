package kr.worthseeing.main.auction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuctionLog is a Querydsl query type for AuctionLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuctionLog extends EntityPathBase<AuctionLog> {

    private static final long serialVersionUID = -2068155292L;

    public static final QAuctionLog auctionLog = new QAuctionLog("auctionLog");

    public final NumberPath<Integer> auction_seq = createNumber("auction_seq", Integer.class);

    public final NumberPath<Integer> auctionLog_seq = createNumber("auctionLog_seq", Integer.class);

    public final NumberPath<Integer> auctionPrice = createNumber("auctionPrice", Integer.class);

    public final NumberPath<Integer> finishPrice = createNumber("finishPrice", Integer.class);

    public final NumberPath<Integer> reservation_seq = createNumber("reservation_seq", Integer.class);

    public final DateTimePath<java.util.Date> suggestDate = createDateTime("suggestDate", java.util.Date.class);

    public final NumberPath<Integer> suggestPrice = createNumber("suggestPrice", Integer.class);

    public final StringPath userId = createString("userId");

    public QAuctionLog(String variable) {
        super(AuctionLog.class, forVariable(variable));
    }

    public QAuctionLog(Path<? extends AuctionLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuctionLog(PathMetadata metadata) {
        super(AuctionLog.class, metadata);
    }

}

