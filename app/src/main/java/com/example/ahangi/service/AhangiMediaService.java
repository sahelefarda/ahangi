package com.example.ahangi.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.LibraryResult;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class AhangiMediaService extends MediaLibraryService {
    private MediaLibrarySession mediaLibrarySession;
    private ExoPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new ExoPlayer.Builder(this).build();
        mediaLibrarySession = new MediaLibrarySession.Builder(this, player, new MediaLibrarySessionCallback())
                .setId("AhangiMediaSession")
                .build();
    }

    @Nullable
    @Override
    public MediaLibrarySession onGetSession(@NonNull MediaSession.ControllerInfo controllerInfo) {
        return mediaLibrarySession;
    }

    @Override
    public void onDestroy() {
        if (mediaLibrarySession != null) {
            mediaLibrarySession.release();
            mediaLibrarySession = null;
        }
        if (player != null) {
            player.release();
            player = null;
        }
        super.onDestroy();
    }

    private class MediaLibrarySessionCallback implements MediaLibrarySession.Callback {
        @NonNull
        @Override
        public ListenableFuture<LibraryResult<MediaItem>> onGetLibraryRoot(
                @NonNull MediaLibrarySession session,
                @NonNull MediaSession.ControllerInfo browser,
                @Nullable MediaLibraryService.LibraryParams params) {
            MediaItem rootItem = new MediaItem.Builder()
                    .setMediaId("root")
                    .setMediaMetadata(new MediaMetadata.Builder()
                            .setIsPlayable(false)
                            .setIsBrowsable(true)
                            .setTitle("Ahangi")
                            .build())
                    .build();
            return Futures.immediateFuture(LibraryResult.ofItem(rootItem, params));
        }

        @NonNull
        @Override
        public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetChildren(
                @NonNull MediaLibrarySession session,
                @NonNull MediaSession.ControllerInfo browser,
                @NonNull String parentId,
                int page,
                int pageSize,
                @Nullable MediaLibraryService.LibraryParams params) {
            return Futures.immediateFuture(LibraryResult.ofItemList(ImmutableList.of(), params));
        }
    }
}
