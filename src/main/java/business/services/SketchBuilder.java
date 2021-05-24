package business.services;

import business.entities.Carport;
import business.entities.Item;
import business.entities.Request_obj;
import business.entities.SVG;
import business.exceptions.UserException;
import business.persistence.Database;

import java.util.List;

public class SketchBuilder {

    public class SvgBuilder {
        SVG svg;


        public SvgBuilder(Database database) {
            ItemFacade itemFacade = new ItemFacade(database);
            SVG svg = new SVG(0,0,"0",0,0);

        }

        private Item getType(List<Item> itemList, String categoryName) {
            for (Item item : itemList) {
                if (categoryName.equals(item.getName())) {
                    return item;
                }
            }
            return null;
        }

        private int typeCount(List<Item> itemList, String categoryName) {
            int count = 0;
            for (Item item : itemList) {
                if (categoryName.equals(item.getName())) {
                    count += item.getQuantity();
                }
            }
            return count;
        }

        public String draw(Request_obj request_obj) {
            double width = request_obj.getCarport().getWidth();
            double length = request_obj.getCarport().getLength();
            double shedWidth = request_obj.getCarport().getShed_width();
            double shedLength = request_obj.getCarport().getShed_length();
            // size units
            int ti = 4; // tiny
            int sm = ti * 2; // small
            int md = sm * 2; // medium
            int lg = md * 2; // large

            int posts = typeCount(request_obj.getItemList(), "item_name");
            int rafters = typeCount(request_obj.getItemList(), "item_name");
            Carport carport = request_obj.getCarport();

            // frame
            svg.addFilledRect(0, 0, width, length);
            svg.addRect(ti, ti, width - sm, length - sm);

            // Draw 2 posts top, 2 in the bottom, or past large sheds
            double lastPostY = length - lg;
            if (carport.HasShed()) {

                lastPostY -= (carport.HasShed_large()) ? shedLength + lg * 4 : shedLength - md;
                svg.addRect(md, md, md, md); // fixed post top
                svg.addRect(width - lg, md, md, md); // fixed post top
                svg.addRect(md, lastPostY, md, md); // fixed post bottom
                svg.addRect(width - lg, lastPostY, md, md); // fixed post bottom
            }
            // draw shed with posts
            if (carport.HasShed()) {
                double shedX = (carport.HasShed_large()) ? (carport.getShed_width()) / 2d : md; // center big sheds
                double shedY = length - shedLength;
                shedY -= (carport.HasShed_large()) ? carport.getShed_width() / 2d : md; // center big sheds
                svg.addFilledRect(shedX, shedY, shedWidth, shedLength); // shed
                svg.addRect(shedX, shedY, md, md); // shedpost top left
                svg.addRect(shedX + shedWidth - md, shedY, md, md); // shedpost top right
                svg.addRect(shedX, shedY + shedLength - md, md, md); // shedpost bottom left
                svg.addRect(shedX + shedWidth - md, shedY + shedLength - md, md, md); // shedpost bottom right
                if (!carport.HasShed_large()) svg.addRect(width - lg, length - lg, md, md); // post opposite small shed
            }

            // add center post if length between corner posts require additional support
            if ((lastPostY - md) / 2 > 310) {
                double centerPostY = (lastPostY - md) / 2;
                svg.addRect(md, centerPostY, md, md);
                svg.addRect(width - lg, centerPostY, md, md);
            }
            // rim / rem
            svg.addRect(sm, ti, sm, length - sm);
            svg.addRect(width - md, ti, sm, length - sm);

            // rafter / spæretræ
            Item item = getType(request_obj.getItemList(), "item_name");
            try {
                for (int i = 0; i <= item.getQuantity(); i++) {
                    double gab = (length - lg) / item.getQuantity();
                    svg.addEmptyRect(0, md + gab * i, width, ti);
                }
            } catch (NullPointerException ex) {
                for (int i = 0; i <= (int) Math.ceil(request_obj.getCarport().getLength() / 55d); i++) {
                    double gab = (length - lg) / item.getQuantity();
                    svg.addEmptyRect(0, md + gab * i, width, ti);
                }
                ex.printStackTrace();
            }

            // roof
            double roofY2 = length - md;
            if (carport.HasShed_large()) roofY2 -= shedLength;
            svg.addDottedLine(md, md, width - md, roofY2);
            svg.addDottedLine(width - md, md, md, roofY2);
            return toString();
        }
    }
}
